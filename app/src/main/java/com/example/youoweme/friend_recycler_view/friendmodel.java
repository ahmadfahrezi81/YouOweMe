package com.example.youoweme.friend_recycler_view;

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
