package com.gbnsolutions.projectfreelancing.model

class Domain {
    private var domain: String = ""
    private var domainpic: String = ""

    constructor()
    constructor(domain: String,domainpic: String) {
        this.domain = domain
        this.domainpic = domainpic
    }
    fun getDomain(): String?{
        return domain
    }
    fun setDomain(domain: String){
        this.domain = domain
    }
    fun getDomainPic(): String?{
        return domainpic
    }
    fun setDomainPic(domainpic: String){
        this.domainpic = domainpic
    }
}