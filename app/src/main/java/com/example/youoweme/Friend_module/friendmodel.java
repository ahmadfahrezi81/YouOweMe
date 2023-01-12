package com.example.youoweme.Friend_module;

public class friendmodel {
    String friendname;
    String debtstatus;
    String debtamount;
    int image;

    public friendmodel(String friendname, String debtstatus, String debtamount, int image) {
        this.friendname = friendname;
        this.debtstatus = debtstatus;
        this.debtamount = debtamount;
        this.image = image;
    }

    public String getFriendname() {
        return friendname;
    }

    public String getDebtstatus() {
        return debtstatus;
    }

    public String getDebtamount() {
        return debtamount;
    }

    public int getImage() {
        return image;
    }
}
