package com.mashup.gabbangzip.sharedalbum.domain.usecase.vote

import com.mashup.gabbangzip.sharedalbum.domain.repository.VoteRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetVoteVisitUseCase @Inject constructor(
    private val voteRepository: VoteRepository,
) {
    operator fun invoke(): Boolean {
        return voteRepository.getVoteFirstVisit()
    }
}
