package fr.insalyon.h4112.controller;

import fr.insalyon.h4112.Service.VoterService;
import fr.insalyon.h4112.Utility.ResultMapFactory;
import fr.insalyon.h4112.model.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
    public Map<String,Object> saveVoter(String familyName, String givenName, String login, String password){
        Map<String,Object> map=new HashMap<String,Object>();
        Voter voter=null;
        try {
            voter=voterService.registerVoter(familyName,givenName,login,password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        if(voter!=null) {
            map.put("voter", voter);
            return ResultMapFactory.getSuccessResultMap(map);
        }
        return ResultMapFactory.getErrorResultMap("");
    }
    @RequestMapping(value="/session", method = { RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> login(String login, String password, HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        Voter voter=voterService.loginVoter(login,password);
        if(voter!=null){
            map.put("voter",voter);
            session.setAttribute("voter",voter);
            return ResultMapFactory.getSuccessResultMap(map);
        }
        return ResultMapFactory.getErrorResultMap("");
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
