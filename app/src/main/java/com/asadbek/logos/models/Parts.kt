package com.asadbek.logos.models

class Parts {
    var id:String? = null
    var name:String? = null
    var imageToken:String? = null
    constructor()
    constructor(id: String?, name: String?, imageToken: String?) {
        this.id = id
        this.name = name
        this.imageToken = imageToken
    }

    constructor(name: String?, imageToken: String?) {
        this.name = name
        this.imageToken = imageToken
    }


}