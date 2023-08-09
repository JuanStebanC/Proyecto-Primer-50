package edu.uptc.model;

public class ServicePhone {
	private Phone phoneOne;
    private Phone phoneTwo;
    private Phone phoneThree;

    public void setPhoneOne(Phone phoneOne) {

        if (this.phoneOne == null) {

            try {
                if (phoneTwo != null && phoneTwo.getImei().equals(phoneOne.getImei())) {
                    throw new IllegalArgumentException();
                }
                if (phoneThree != null && phoneThree.getImei().equals(phoneOne.getImei())) {
                    throw new IllegalArgumentException();
                }
                this.phoneOne = phoneOne;
            } catch (Exception e) {
                
            }
        }
    }

    public void setPhoneTwo(Phone phoneTwo) {
        if (this.phoneTwo == null) {
            try {
                if (phoneOne != null && phoneTwo.getImei().equals(phoneOne.getImei())) {
                    throw new IllegalArgumentException();
                }
                if (phoneThree != null && phoneTwo.getImei().equals(phoneThree.getImei())) {
                    throw new IllegalArgumentException();
                }
                this.phoneTwo = phoneTwo;
            } catch (Exception e) {
                
            }
        }
    }

    public void setPhoneThree(Phone phoneThree) {
        if (this.phoneThree == null) {
            try {
                if (phoneOne != null && phoneThree.getImei().equals(phoneOne.getImei())) {
                    throw new IllegalArgumentException();
                }
                if (phoneTwo != null && phoneThree.getImei().equals(phoneTwo.getImei())) {
                    throw new IllegalArgumentException();
                }
                this.phoneThree = phoneThree;
            } catch (Exception e) {
                
            }
        }
    }

    public Phone getPhoneOne() {
        return phoneOne;
    }

    public Phone getPhoneTwo() {
        return phoneTwo;
    }

    public Phone getPhoneThree() {
        return phoneThree;
    }
    
    public boolean registryCall(Phone phone, int minutes, ETypeCall eTypeCall){

        int oldMinutes = phone.getCellPlan().getMinutes();
        int newMinutes = 0;

        if (eTypeCall == ETypeCall.MOVIL) {

            if (oldMinutes < 1) {
                return false;
            } 

            newMinutes = oldMinutes - minutes;
            phone.getCellPlan().setMinutes(newMinutes);
            return true;

        } else if (eTypeCall == ETypeCall.FIXED){

            if (oldMinutes < 2) {
                return false;
            } 
            
            newMinutes = oldMinutes - (minutes * 2);
            phone.getCellPlan().setMinutes(newMinutes);
            return true;

        } else if (eTypeCall == ETypeCall.INTERNATIONAL){

            if (oldMinutes < 3) {
                return false;
            } 

            newMinutes = oldMinutes - (minutes * 3);
            phone.getCellPlan().setMinutes(newMinutes);
            return true;

        }
        return false;
    }

    public Phone findPhone(String imei){
        
        if (phoneOne.getImei().equals(imei)){
            return phoneOne;
        } else if (phoneTwo.getImei().equals(imei)){
            return phoneTwo;
        } else if (phoneThree.getImei().equals(imei)){
            return phoneThree;
        }
        return null;
    }

    public int addMinutes(Phone phone){

        
        if (findPhone(phone.getImei()) != null) {
            
            return phone.getCellPlan().getMinutes();
        }        

        return 0;
    }
	
}
