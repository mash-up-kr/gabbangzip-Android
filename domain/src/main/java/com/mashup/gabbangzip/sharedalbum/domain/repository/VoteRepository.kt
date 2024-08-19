package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VotePhotoDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultParam

interface VoteRepository {
    suspend fun requestVoteResult(param: VoteResultParam): VoteResultDomainModel
    suspend fun getVotePhotoList(eventId: Long): List<VotePhotoDomainModel>
    fun getVoteFirstVisit(): Boolean
    fun saveVoteFirstVisit(isFirstVisit: Boolean)
}
