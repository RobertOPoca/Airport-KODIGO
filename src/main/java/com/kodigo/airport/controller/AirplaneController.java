
package com.kodigo.airport.controller;

import com.kodigo.airport.dto.AirplaneDTO;
import com.kodigo.airport.item.IItemAirplane;
import com.kodigo.airport.model.Airplane;
import com.kodigo.airport.responses.ResponseApi;
import com.kodigo.airport.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    @GetMapping()
    public ResponseApi<List<IItemAirplane>> getAllAirplanes(){
        boolean success;
        String message;

        List<IItemAirplane> itemAirplaneList = new ArrayList<>();
        List<Airplane> airplaneList= airplaneService.findAll();
        if(airplaneList.isEmpty()){
            success = false;
            message = "No airlines found";
        }else{
            success = true;
            message = "Airlines found";
            for(Airplane airplane : airplaneList){
                var itemAirplane = new IItemAirplane();
                itemAirplane.setModel(airplane.getModel());
                itemAirplane.setReach(airplane.getReach());
                itemAirplaneList.add(itemAirplane);
            }
        }
        return new ResponseApi <>(success, message, itemAirplaneList);
    }

    @GetMapping("/{model}")
    public ResponseApi<IItemAirplane> findById(@PathVariable("model") String model){
        boolean success;
        String message;
        IItemAirplane itemAirplane= new IItemAirplane();
        Airplane airplane = airplaneService.findById(model);
        if(airplane==null){
            success = false;
            message = "No airplane found";
        }else{
            success = true;
            message = "Airplane found";

            itemAirplane.setModel(airplane.getModel());
            itemAirplane.setReach(airplane.getReach());
        }
        return new ResponseApi<>(success, message, itemAirplane);

    }

    @PutMapping
    public ResponseApi<IItemAirplane> update(@RequestBody AirplaneDTO airplaneDTO){
        boolean success = false;
        String message = "";
        Airplane airplane = new Airplane();
        IItemAirplane itemAirplane = new IItemAirplane();
        try{
            airplane.setModel(airplaneDTO.getModel());
            airplane.setReach(airplaneDTO.getReach());
            airplane = this.airplaneService.update(airplane);
            if(airplane!=null){
                itemAirplane.setModel(airplane.getModel());
                itemAirplane.setReach(airplane.getReach());
                success = true;
                message = "Airplane was updated successfully";
            }else {
                success = false;
                message = "Error";
            }
        }catch (Exception ex){
            ex.printStackTrace();
            message = ex.getMessage();
        }
        return new ResponseApi<>(success, message, itemAirplane);
    }

}

