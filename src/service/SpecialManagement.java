package service;

import dao.*;
import dto.*;

import java.sql.*;
import java.util.*;

public class SpecialManagement {


    // Suggest one operation daily.
    public void updateAll() {

        try {
            List<String> dealerIDs = new DealerManagerImpl().getAllDealerIDs();
            if (dealerIDs == null || dealerIDs.isEmpty()) {
                return;
            }

            for (String id : dealerIDs) {
                update(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Suggest every time after one rule applied.
    public void update(String dealerID) {

        try {
            List<Special> specials = new SpecialManagerImpl().getValidSpecialsByDealerID(dealerID);
            if (specials == null || specials.isEmpty()) {
                return;
            }

            VehicleFilterSelected p = new VehicleFilterSelected(dealerID);
            List<Vehicle> vehicles = new VehicleManagerImpl().getAllVehiclesByFilter(p);

            //@todo Assign different applicable specials to different vehicles.
            //this

            //@todo inside different vehicle, calculate the minimum value, and remove those 'useless' specials.


            new VehicleManagerImpl().updateFinalPriceAndDiscount(vehicles);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void storeSpecial(Special special) {
        SpecialManagerImpl ms = new SpecialManagerImpl();
        ms.addSpecial(special);
    }


    public void deleteSpecial(Special special){
        SpecialManagerImpl ms = new SpecialManagerImpl();
        ms.removeSpecial(special);
    }

    public Special getSpecialBySpecialID(String id){
        SpecialManagerImpl sq = new SpecialManagerImpl();
        Special result = null;
        try {
             result = sq.getSpecialByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    public List<Special> getSpecialsByDealerID(String id){
        SpecialManagerImpl sq = new SpecialManagerImpl();
        List<Special> result = new ArrayList<>();
        try {
            result = sq.getAllSpecialsByDealerID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
