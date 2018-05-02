package fr.insalyon.h4112.controller;

import fr.insalyon.h4112.Service.ElectionService;
import fr.insalyon.h4112.Service.VoterService;
import fr.insalyon.h4112.Utility.ResultMapFactory;
import fr.insalyon.h4112.model.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.crypto.ECKeyPair;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;


@Controller
    public class KeyController{

        @Autowired
        private VoterService voterService;
        @Autowired
        private ElectionService electionService;

    @RequestMapping(value="/key", method = { RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getKey(){
        Map<String,Object> map=new HashMap<String,Object>();
        ECKeyPair ecKeyPair=null;
        try {
            ecKeyPair=electionService.generateKeyPair();
        } catch (InvalidAlgorithmParameterException e) {
            return ResultMapFactory.getErrorResultMap(e);
        } catch (NoSuchAlgorithmException e) {
            return ResultMapFactory.getErrorResultMap(e);
        } catch (NoSuchProviderException e) {
            return ResultMapFactory.getErrorResultMap(e);
        }
        map.put("publicKey",ecKeyPair.getPublicKey().toString(16));
        map.put("privateKey",ecKeyPair.getPrivateKey().toString(16));

        return ResultMapFactory.getSuccessResultMap(map);
    }

    }
