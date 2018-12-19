package com.vaclavstrnad.dbsync

import org.bson.Document

data class Document(val id: String, val value: String, val version: Int) : Document() {

    constructor() : this("", "", 0)

}