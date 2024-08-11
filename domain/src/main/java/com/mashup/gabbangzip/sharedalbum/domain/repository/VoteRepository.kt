package com.mashup.gabbangzip.sharedalbum.domain.repository

import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultParam

interface VoteRepository {
    suspend fun requestVoteResult(param: VoteResultParam): VoteResultDomainModel
}