package com.gbnsolutions.projectfreelancing.model

class Slots {
    private var slotid: String = ""
    private var slot: String = ""

    constructor()
    constructor(slotid: String, slot: String) {
        this.slotid = slotid
        this.slot = slot
    }


    fun getSlot():String?{
        return slot
    }
    fun setSlot(slot: String){
        this.slot = slot
    }
    fun getSlotId():String?{
        return slotid
    }
    fun setSlotId(slotid: String){
        this.slotid = slotid
    }

}