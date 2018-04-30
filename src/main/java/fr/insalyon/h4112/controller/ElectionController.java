package fr.insalyon.h4112.controller;

import fr.insalyon.h4112.Service.ElectionService;
import fr.insalyon.h4112.Service.VoterService;
import fr.insalyon.h4112.Utility.ResultMapFactory;
import fr.insalyon.h4112.model.Candidate;
import fr.insalyon.h4112.model.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by siyingjiang on 2018/4/26.
 */

@Controller
public class ElectionController {

    @Autowired
    private VoterService voterService;
    @Autowired
    private ElectionService electionService;



    @RequestMapping(value="/elections", method = { RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> createElection(@RequestBody Election election){
        Map<String,Object> map=new HashMap<String,Object>();
        Election e=electionService.registerElection(election);
        map.put("election",e);
        return ResultMapFactory.getSuccessResultMap(map);
    }

    @RequestMapping(value="/elections", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> showElections(){
        Map<String,Object> map=new HashMap<String,Object>();
        List<Election> electionList=electionService.getElectionList();
        map.put("elections",electionList);
        return ResultMapFactory.getSuccessResultMap(map);
    }

    @RequestMapping(value="/elections/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> showElection(@PathVariable("id") Integer id){
        Map<String,Object> map=new HashMap<String,Object>();
        Election election=electionService.getElection(id);
        map.put("election",election);
        return ResultMapFactory.getSuccessResultMap(map);
    }

    @RequestMapping(value="/elections/{id}", method = {RequestMethod.PUT})
    @ResponseBody
    public Map<String,Object> participateVote(String idVoterPubKey, @PathVariable("id") Integer idElection){
        Map<String,Object> map=new HashMap<String,Object>();
        //TODO: no passable exception
        electionService.addVoterToElection(Integer.parseInt(idVoterPubKey),idElection);
        return ResultMapFactory.getSuccessResultMap(map);


    }


}
