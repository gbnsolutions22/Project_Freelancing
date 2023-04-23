package com.gbnsolutions.projectfreelancing.model

class Users {
    private var UID: String = ""
    private var Name: String = ""
    private var profile: String = ""
    private var Email: String = ""
    private var PhoneNumber: String = ""

    constructor()
    constructor(UID: String, Name: String, profile: String, Email: String, PhoneNumber: String) {
        this.UID = UID
        this.Name = Name
        this.profile = profile
        this.Email = Email
        this.PhoneNumber = PhoneNumber
    }

    fun getUID():String?{
        return UID
    }
    fun setUID(UID: String){
        this.UID = UID
    }
    fun getName():String?{
        return Name
    }
    fun setName(Name: String){
        this.Name = Name
    }
    fun getEmail():String?{
        return Email
    }
    fun setEmail(Email: String){
        this.Email = Email
    }
    fun getPhoneNumber():String?{
        return PhoneNumber
    }
    fun setPhoneNumber(PhoneNumber: String){
        this.PhoneNumber = PhoneNumber
    }
    fun getProfile():String?{
        return profile
    }
    fun setProfile(profile: String){
        this.profile = profile
    }

}