package edu.unomaha.peerreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

//import java.util.List;
import edu.unomaha.peerreview.model.PeerReviewData;

// This will be AUTO IMPLEMENTED by Spring into a Bean called PeerReviewRepository
// CRUD refers Create, Read, Update, Delete

public interface PeerReviewDataRepository extends CrudRepository<PeerReviewData, Integer> {
	
	/*@Override
	@Query("SELECT pd, p FROM PeerReviewData pd LEFT JOIN pd.peerreview p")
    List<PeerReviewData> findAll();
*/
}