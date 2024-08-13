package com.mashup.gabbangzip.sharedalbum.domain.usecase.vote

import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VoteResultParam
import com.mashup.gabbangzip.sharedalbum.domain.repository.VoteRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RequestVoteResultUseCase @Inject constructor(
    private val voteRepository: VoteRepository,
) {
    suspend operator fun invoke(param: VoteResultParam): Result<VoteResultDomainModel> {
        return runCatching {
            voteRepository.requestVoteResult(param)
        }
    }
}
