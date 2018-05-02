package fr.insalyon.h4112.controller;

import fr.insalyon.h4112.Service.VoterService;
import fr.insalyon.h4112.Utility.ResultMapFactory;
import fr.insalyon.h4112.model.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by siyingjiang on 2018/4/30.
 */
@RestController
public class VoterController {

    @Autowired
    private VoterService voterService;

    @RequestMapping(value="/voter", method = { RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> saveVoter(String familyName, String givenName, String login, String password,String address,String birthday){
        Map<String,Object> map=new HashMap<String,Object>();
        Voter voter=null;

        if (voterService.verifyLoginExistence(login)) {
            return ResultMapFactory.getErrorResultMap("Login existe deja, veuillez changer un login");
        }

        try {
            voter=voterService.registerVoter(familyName,givenName,login,password,address,birthday);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResultMapFactory.getErrorResultMap("Inscription echoue, veuillez reessayer");
        }
        if(voter!=null) {
            map.put("voter", voter);
            return ResultMapFactory.getSuccessResultMap(map);
        }
        return ResultMapFactory.getErrorResultMap("Inscription echoue, veuillez reessayer");
    }

    @RequestMapping(value="/voter", method = { RequestMethod.PUT})
    @ResponseBody
    public Map<String,Object> updateMdp(String oldPassword, String password, HttpSession session ) {
        Voter v= (Voter) session.getAttribute("voter");
        if (v.getHashPassword().equals(""+oldPassword.hashCode())) {
            v.setHashPassword(password.hashCode()+"");
            voterService.updateVoter(v);
            return ResultMapFactory.getSuccessResultMap();

        }
        else {
            return ResultMapFactory.getErrorResultMap("password erreur");
        }

    }
    @RequestMapping(value="/voter/{id}", method = { RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getVoter(@PathVariable("id") Integer id) {
        Map<String,Object> map=new HashMap<String,Object>();
        Voter v=voterService.getVoter(id);
        if (v==null) {
            return ResultMapFactory.getErrorResultMap("Le voter que vous avez demander n'existe pas");
        } else {
            map.put("voter",v);
            return ResultMapFactory.getSuccessResultMap(map);
        }
    }

    @RequestMapping(value="/session", method = { RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> login(String login, String password, HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        if (!voterService.verifyLoginExistence(login)) {
            return ResultMapFactory.getErrorResultMap("Login n'existe pas");
        }

        Voter voter=voterService.loginVoter(login,password);
        if(voter!=null){
            map.put("voter",voter);
            session.setAttribute("voter",voter);
            return ResultMapFactory.getSuccessResultMap(map);
        }
        return ResultMapFactory.getErrorResultMap("password erreur");
    }

    @RequestMapping(value="/session", method = { RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getSeesion(HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        Voter voter= (Voter) session.getAttribute("voter");
        if(voter!=null){
            map.put("voter",voter);
            return ResultMapFactory.getSuccessResultMap(map);
        }
        return ResultMapFactory.getErrorResultMap("");
    }

    @RequestMapping(value="/session", method = { RequestMethod.DELETE})
    @ResponseBody
    public Map<String,Object> deleteSeesion(HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        session.removeAttribute("voter");
        return ResultMapFactory.getSuccessResultMap(map);
    }
}
