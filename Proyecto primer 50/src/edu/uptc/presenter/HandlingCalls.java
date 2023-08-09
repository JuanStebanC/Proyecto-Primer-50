package edu.uptc.presenter;

import java.time.LocalDate;

import edu.uptc.model.CellPlan;
import edu.uptc.model.ETypeCall;
import edu.uptc.model.Phone;
import edu.uptc.model.ServicePhone;

public class HandlingCalls {

    private ServicePhone servicePhone = new ServicePhone();

    public HandlingCalls() {
    }
    
    public boolean addPhone(String imei, String number, LocalDate date, String description, int minutes){
        Phone phone = new Phone(new CellPlan(description, minutes), imei, number, date);
        

        if (servicePhone.getPhoneOne() == null) {

            servicePhone.setPhoneOne(phone);
            return true;

        } else if (servicePhone.getPhoneTwo() == null) {

            servicePhone.setPhoneTwo(phone);
            return true;

        } else if (servicePhone.getPhoneThree() == null){

            servicePhone.setPhoneThree(phone);
            return true;
        }
        return false;
    }   


    public String findPhone(String imei){
        
        Phone findPhone = servicePhone.findPhone(imei);

        if (findPhone != null) {
            return findPhone.toString();
        } 

        return "Phone not found";
    }

    public int addMinutes(String imei, int minutes){

        int newMinutes = 0;
        Phone findPhone = servicePhone.findPhone(imei);
        CellPlan cellPlan = findPhone.getCellPlan();
                
        servicePhone.addMinutes(findPhone);

        if (findPhone != null) {   

            newMinutes = cellPlan.getMinutes() + minutes;
            cellPlan.setMinutes(newMinutes);
            return newMinutes;
        }        

        return 0;
    }

    public boolean registryCall(String imei, String number, int minutes) {
        Phone phone = servicePhone.findPhone(imei);
        String numberStr = String.valueOf(number); 

        if (phone != null) {
            CellPlan cellPlan = phone.getCellPlan();
            
            if (numberStr.length() == 10 && cellPlan.getMinutes() >= minutes) {
                int deductedMinutes = minutes;
                return servicePhone.registryCall(phone, deductedMinutes, ETypeCall.MOVIL);

            } else if (numberStr.length() == 6 && cellPlan.getMinutes() >= minutes * 2) {
                int deductedMinutes = minutes * 2;
                return servicePhone.registryCall(phone, deductedMinutes, ETypeCall.FIXED);

            } else if (cellPlan.getMinutes() >= minutes * 3) {
                int deductedMinutes = minutes * 3;
                return servicePhone.registryCall(phone, deductedMinutes, ETypeCall.INTERNATIONAL);
            }
        }

        return false;
    }



    


}
