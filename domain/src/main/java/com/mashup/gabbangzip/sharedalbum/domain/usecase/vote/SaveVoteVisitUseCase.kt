package com.mashup.gabbangzip.sharedalbum.domain.usecase.vote

import com.mashup.gabbangzip.sharedalbum.domain.repository.VoteRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SaveVoteVisitUseCase @Inject constructor(
    private val voteRepository: VoteRepository,
) {
    operator fun invoke(isFirstVisit: Boolean) {
        voteRepository.saveVoteFirstVisit(isFirstVisit)
    }
}
