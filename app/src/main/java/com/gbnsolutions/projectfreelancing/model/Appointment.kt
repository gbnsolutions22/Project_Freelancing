package com.gbnsolutions.projectfreelancing.model

class Appointment {
    private var uid: String = ""
    private var domain: String = ""
    private var slot: String = ""
    private var title: String = ""
    private var domainpic: String = ""
    private var slotid: String = ""
    constructor()
    constructor(uid: String, domain: String, slot: String, title: String, domainpic: String, slotId: String) {
        this.uid = uid
        this.domain = domain
        this.slot = slot
        this.title = title
        this.domainpic = domainpic
        this.slotid = slotid
    }
    fun getUID():String?{
        return uid
    }
    fun setUID(uid: String){
        this.uid = uid
    }
    fun getDomain(): String?{
        return domain
    }
    fun setDomain(domain: String){
        this.domain = domain
    }
    fun getSlot(): String?{
        return slot
    }
    fun setSlot(slot: String){
        this.slot = slot
    }
    fun getTitle(): String?{
        return title
    }
    fun setTile(title: String){
        this.title = title
    }
    fun getDomainPic():String?{
        return domainpic
    }
    fun setDomainPic(domainpic: String){
        this.domainpic = domainpic
    }
    fun getSlotId():String?{
        return slotid
    }
    fun setSlotId(slotid: String){
        this.slotid = slotid
    }
}