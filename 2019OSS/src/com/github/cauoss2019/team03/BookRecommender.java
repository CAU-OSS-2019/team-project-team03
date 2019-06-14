import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.*;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.GenericBooleanPrefDataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookRecommender {

    private int user_id;

    public BookRecommender(int user_id, int howMany) throws IOException, TasteException {

        ArrayList<Integer> bookIDList = new ArrayList<Integer>();

        // TODO Auto-generated method stub
        DataModel model = new FileDataModel(new File("resultData_ver4.csv"));
        UserSimilarity similarity = new LogLikelihoodSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, model);
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        List<RecommendedItem> recommendations = recommender.recommend(user_id, howMany);
        for (RecommendedItem recommendation : recommendations) {
            bookIDList.add((int) recommendation.getItemID());
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (
                ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/temp?serverTimezone = UTC", "root", "rhkdejr12#");

            PreparedStatement pStmt = conn.prepareStatement("select * from book where book_id = ?");
            for (int i : bookIDList) {
                pStmt.setInt(1, i);

                ResultSet resultSet = pStmt.executeQuery();

                while (resultSet.next()) {
                    System.out.println(resultSet.getString("book_id") + "\t" +
                            resultSet.getString("title") + "\t" +
                            resultSet.getString("author") + "\t" +
                            resultSet.getString("publisher") + "\t" +
                            resultSet.getString("date") + "\t" +
                            resultSet.getString("ranking") + "\t" +
                            resultSet.getString("category"));
                }
            }


        } catch (
                SQLException e) {
            e.printStackTrace();

        }
    }
}