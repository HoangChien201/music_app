package com.example.music_app.data.remote.respon

import com.example.music_app.domain.model.HeaderResp

class BaseListResp<T> (
    val headers:HeaderResp,
    val results:List<T>
)