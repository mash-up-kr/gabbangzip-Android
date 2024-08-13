package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.base.callApi
import com.mashup.gabbangzip.sharedalbum.data.dto.request.vote.toRequestBody
import com.mashup.gabbangzip.sharedalbum.data.dto.response.vote.toDomainModel
import com.mashup.gabbangzip.sharedalbum.data.service.VoteService
import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.VoteRepository
import javax.inject.Inject

class VoteRepositoryImpl @Inject constructor(
    private val voteService: VoteService,
) : VoteRepository {
    override suspend fun requestVoteResult(param: VoteResultParam): VoteResultDomainModel {
        return callApi {
            voteService.requestVoteResult(param.toRequestBody())
        }.toDomainModel()
    }
}
