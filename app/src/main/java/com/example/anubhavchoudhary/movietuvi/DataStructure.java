package com.example.anubhavchoudhary.movietuvi;

// TODO:  Create your own datastructure to hold the data.
public class DataStructure {
    private String profilename;
    private String ProfileCountry;
    private String ProfilePhoneNumber;
    private String ProfileHobby;
    private String ProfileBirthday;
    private String Profilegender;

public DataStructure(){

}
    public DataStructure(String profilename, String ProfileCountry, String ProfilePhoneNumber, String ProfileHobby, String ProfileBirthday, String Profilegender ) {
        this.profilename = profilename;
        this.ProfileCountry = ProfileCountry;
        this.ProfilePhoneNumber =ProfilePhoneNumber;
        this.ProfileHobby = ProfileHobby;
       this.ProfileBirthday = ProfileBirthday;
       this.Profilegender=Profilegender;
    }

    public String getprofilename() {
        return profilename;
    }

    public void setprofilename(String profilename) { this.profilename = profilename;
    }

    public String getProfileCountry() {
        return ProfileCountry;
    }

    public void setProfileCountry(String ProfileCountry) {
        this.ProfileCountry = ProfileCountry;
    }

    public String getProfileHobby() { return ProfileHobby; }

    public void setProfileHobby(String ProfileHobby) {
        this.ProfileHobby = ProfileHobby;
    }

    public String getProfileBirthday() {
        return ProfileBirthday;
    }

    public void setProfileBirthday(String ProfileBirthday) { this.ProfileBirthday = ProfileBirthday; }


    public String getProfilePhoneNumber() {
        return ProfilePhoneNumber;
    }

    public void setProfilePhoneNumber(String ProfilePhoneNumber) { this.ProfilePhoneNumber = ProfilePhoneNumber; }

    public String getProfilegender() {
        return Profilegender;
    }

    public void setProfilegender(String Profilegender) { this.Profilegender = Profilegender; }


}
