import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class BookRecommender {

    private Long userId;
    private DataModel model;
    private HashMap<Long, Book> bookHashMap;

    public BookRecommender(DataModel model, HashMap<Long, Book> bookHashMap, Long userId, int howMany) throws IOException, TasteException {

        // TODO Auto-generated method stub

        this.model = model;
        this.userId = userId;
        this.bookHashMap = bookHashMap;

        UserSimilarity similarity = new LogLikelihoodSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        List<RecommendedItem> recommendations = recommender.recommend(userId, howMany);
        for (RecommendedItem recommendation : recommendations) {
            System.out.println(bookHashMap.get(recommendation.getItemID()).toString());
        }
        createNewInputFile();
        model.refresh(null);
        recommender.refresh(null);
    }

    private void createNewInputFile() throws TasteException, IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter("newResultDataSet.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        for (Long bookId : model.getItemIDsFromUser(userId)) {
            bw.write(userId + "," + bookId);
            bw.newLine();
        }

        Iterator<Long> it = model.getUserIDs();
        while (it.hasNext()) {
            Long currentUserId = it.next();    //user ÇÑ ¸í¾¿ ²¨³¿
            UserPreferenceAnalysis upa = new UserPreferenceAnalysis(currentUserId, bookHashMap, model);
            if (upa.getStandDev() < 10.0) {
                for (Long bookId : model.getItemIDsFromUser(currentUserId)) {
                    bw.write(currentUserId + "," + bookId);
                    bw.newLine();
                }
            }
        }
        bw.close();

        System.out.println("\n=================================================\n");
        DataModel newModel = new FileDataModel(new File("newResultDataSet.csv"));
        UserSimilarity similarity = new LogLikelihoodSimilarity(newModel);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, newModel);
        UserBasedRecommender recommender = new GenericUserBasedRecommender(newModel, neighborhood, similarity);

        FileWriter fw2 = null;
        try {
            fw2 = new FileWriter("resultData_ver5.csv", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw2 = new BufferedWriter(fw2);


        List<RecommendedItem> recommendations = recommender.recommend(userId, 10);
        for (RecommendedItem recommendation : recommendations) {
            System.out.println(bookHashMap.get(recommendation.getItemID()).toString());
            bw2.write(userId+","+recommendation.getItemID());
            bw2.newLine();
        }
        bw2.close();
    }
}