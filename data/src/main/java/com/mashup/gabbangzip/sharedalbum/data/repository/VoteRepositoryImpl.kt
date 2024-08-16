package com.mashup.gabbangzip.sharedalbum.data.repository

import com.mashup.gabbangzip.sharedalbum.data.base.callApi
import com.mashup.gabbangzip.sharedalbum.data.dto.request.vote.toRequestBody
import com.mashup.gabbangzip.sharedalbum.data.dto.response.vote.toDomainModel
import com.mashup.gabbangzip.sharedalbum.data.service.VoteService
import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VotePhotoDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.datasource.LocalDataSource
import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.VoteRepository
import javax.inject.Inject

class VoteRepositoryImpl @Inject constructor(
    private val voteService: VoteService,
    private val localDataSource: LocalDataSource,
) : VoteRepository {
    override suspend fun requestVoteResult(param: VoteResultParam): VoteResultDomainModel {
        return callApi {
            voteService.requestVoteResult(param.toRequestBody())
        }.toDomainModel()
    }

    override suspend fun getVotePhotoList(eventId: Long): List<VotePhotoDomainModel> {
        return callApi {
            voteService.getVotePhotoList(eventId)
        }.toDomainModel()
    }

    override fun getVoteFirstVisit(): Boolean {
        return localDataSource.getVoteFirstVisit()
    }

    override fun saveVoteFirstVisit(isFirstVisit: Boolean) {
        localDataSource.saveVoteFirstVisit(isFirstVisit)
    }
}
