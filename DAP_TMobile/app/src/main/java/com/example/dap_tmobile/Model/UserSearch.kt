package com.example.dap_tmobile.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserSearch {
    @SerializedName("total_count")
    @Expose
    var totalCount: Int? = null
    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean? = null
    @SerializedName("items")
    @Expose
    var items: List<Item>? = null
}