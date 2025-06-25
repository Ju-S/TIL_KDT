package models;

import classes.RentalDTO;

public class RentalDAO {
    private RentalDTO[] rentalDTOList = new RentalDTO[3];
    int currentRentalId = 1001;

    public RentalDTO[] getRentalDTOList() {
        return rentalDTOList;
    }

    public void setRentalDTOList(RentalDTO[] rentalDTOList) {
        this.rentalDTOList = rentalDTOList;
    }

    public void addRentalDTOToIndex(RentalDTO rentalDTO, int index) {
        rentalDTO.setId(currentRentalId++);
        rentalDTOList[index] = rentalDTO;
    }

    public boolean modifyRentalDTOById(RentalDTO modifyRentalDTOInfo, int id) {
        int targetDTOIndex = findRentalById(id);
        if(targetDTOIndex != -1){
            rentalDTOList[targetDTOIndex].setName(modifyRentalDTOInfo.getName());
            rentalDTOList[targetDTOIndex].setCarId(modifyRentalDTOInfo.getCarId());
            rentalDTOList[targetDTOIndex].setRentalStartDate(modifyRentalDTOInfo.getRentalStartDate());
            rentalDTOList[targetDTOIndex].setRentalEndDate(modifyRentalDTOInfo.getRentalEndDate());
            return true;
        } else {
            return false;
        }
    }

    public boolean removeRentalDTO(int index) {
        if(rentalDTOList[index] != null) {
            rentalDTOList[index] = null;
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmptyRentalArray(RentalDTO[] rentalDTOList) {
        for (int j = 0; j < rentalDTOList.length; j++) {
            if (rentalDTOList[j] != null) {
                return false;
            }
        }
        return true;
    }

    public RentalDTO[] findRentalByName(String name) {
        RentalDTO[] result = new RentalDTO[rentalDTOList.length];

        for (int i = 0; i < rentalDTOList.length; i++) {
            if (rentalDTOList[i] != null) {
                if (rentalDTOList[i].getName().contains(name)) {
                    result[i] = rentalDTOList[i];
                }
            }
        }
        return result;
    }

    public int findRentalById(int id) {
        for (int i = 0; i < rentalDTOList.length; i++) {
            if (rentalDTOList[i] != null) {
                if (rentalDTOList[i].getId() == id) {
                    return i;
                }
            }
        }
        return -1;
    }
}
