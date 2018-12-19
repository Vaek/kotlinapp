package com.vaclavstrnad.dbsync

import android.util.Log
import com.mongodb.client.MongoClient
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.android.core.StitchAppClient
import com.mongodb.stitch.android.services.mongodb.local.LocalMongoDbService
import org.bson.BsonString


class DBSync(val originIp: String, val dbName: String) {

    private val client: StitchAppClient
    private val mobileClient: MongoClient

    init {
        // Create the default Stitch Client
        client = Stitch.initializeDefaultAppClient("<APP ID>");

        // Create a Client for MongoDB Mobile (initializing MongoDB Mobile)
        mobileClient = client.getServiceClient(LocalMongoDbService.clientFactory);
    }

    fun store(data: Document) {
// Point to the target collection and insert a document
        val localCollection = mobileClient.getDatabase(dbName).getCollection("my_collection")

        localCollection.insertOne(data)

// Find the first document
        val doc = localCollection.find().first()

//Find all documents that match the find criteria
        val query = Document()
        query.put("id", BsonString("f786dh68ud8B8idbd7UJd"))

        val cursor = localCollection.find(query).into(ArrayList()) as ArrayList<*>
        Log.d("xoxo", cursor.toString())
    }
}