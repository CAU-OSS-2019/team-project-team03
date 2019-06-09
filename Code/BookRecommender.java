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

    public BookRecommender() throws IOException, TasteException {

        ArrayList<Integer> bookIDList = new ArrayList<Integer>();

        // TODO Auto-generated method stub
        DataModel model = new FileDataModel(new File("resultData_ver4.csv"));
        UserSimilarity similarity = new LogLikelihoodSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, model);
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        List<RecommendedItem> recommendations = recommender.recommend(1, 10);
        for (
                RecommendedItem recommendation : recommendations) {
            System.out.println(recommendation);
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

            PreparedStatement pStmt = conn.prepareStatement("select book_id, title, category from book where book_id = ?");
            for (int i : bookIDList) {
                pStmt.setInt(1, i);

                ResultSet resultSet = pStmt.executeQuery();

                while (resultSet.next()) {
                    System.out.println(resultSet.getString("book_id") + "\t" + resultSet.getString("title") + "\t" + resultSet.getString("category"));
                }
            }


        } catch (
                SQLException e) {
            e.printStackTrace();

        }
    }
}

/*

        DataModel model = new GenericBooleanPrefDataModel(GenericBooleanPrefDataModel.toDataMap( new FileDataModel(new File("C:\\Users\\Jin\\Desktop\\ml-100k\\ml-100k\\ua.base"))));
        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
        RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
            public Recommender buildRecommender(DataModel model) throws TasteException {
                UserSimilarity similarity = new LogLikelihoodSimilarity(model);

                UserNeighborhood neighborhood = new NearestNUserNeighborhood(10,similarity, model);

                return new GenericUserBasedRecommender(model, neighborhood, similarity);
            }
        };

        DataModelBuilder modelBuilder = new DataModelBuilder() {
            public DataModel buildDataModel(FastByIDMap<PreferenceArray> trainingData) {
                return new GenericBooleanPrefDataModel(GenericBooleanPrefDataModel.toDataMap(trainingData));
            }
        };
        double score = evaluator.evaluate(recommenderBuilder, modelBuilder, model, 0.9, 1.0);
        System.out.println(score);
*/
        /*
        RandomUtils.useTestSeed();

        DataModel model = new FileDataModel(new File("C:\\Users\\Jin\\Desktop\\ml-100k\\ml-100k\\ua.base"));
        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();

        RecommenderBuilder builder = new RecommenderBuilder() {
            public Recommender buildRecommender(DataModel model) throws TasteException {
                UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

                UserNeighborhood neighborhood = new NearestNUserNeighborhood(2,similarity, model);

                return new GenericUserBasedRecommender(model, neighborhood, similarity);
            }
        };

        double score = evaluator.evaluate(builder, null, model, 0.7, 1.0);
        System.out.println(score);
*/
        /*
                DataModel model = new GenericBooleanPrefDataModel(GenericBooleanPrefDataModel.toDataMap( new FileDataModel(new File("C:\\Users\\Jin\\Desktop\\ml-100k\\ml-100k\\ua.base"))));

                RecommenderIRStatsEvaluator evaluator = new GenericRecommenderIRStatsEvaluator();

                RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
public Recommender buildRecommender(DataModel model) throws TasteException {
        UserSimilarity similarity = new LogLikelihoodSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, model);
        return new GenericUserBasedRecommender(model, neighborhood, similarity);
        }
        };

        DataModelBuilder modelBuilder = new DataModelBuilder() {
public DataModel buildDataModel(FastByIDMap<PreferenceArray> trainingData) {
        return new GenericBooleanPrefDataModel(GenericBooleanPrefDataModel.toDataMap(trainingData));
        }
        };

        IRStatistics stats = evaluator.evaluate(recommenderBuilder, modelBuilder, model, null, 10, GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1.0);

        System.out.println(stats.getPrecision());
        System.out.println(stats.getRecall());





        /*
        List<RecommendedItem> recommendations = recommender.recommend(1,1);

        for (RecommendedItem recommendation : recommendations) {
            System.out.println(recommendation);
        }
        System.out.println("done");
*/
/*
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/temp?serverTimezone = UTC", "root", "rhkdejr12#");

            PreparedStatement pStmt = conn.prepareStatement("insert into checkout values(?,?)");

            FileReader fr = new FileReader("resultData_ver4.csv");
            BufferedReader br = new BufferedReader(fr);

            String str;
            String[] temp;
            int count = 0;
            while ((str = br.readLine()) != null) {
                temp = str.split(",");
                pStmt.setInt(1, Integer.parseInt(temp[0]));
                pStmt.setInt(2, Integer.parseInt(temp[1]));

                pStmt.executeUpdate();
                System.out.println(count + "번 데이터 삽입 완료");
                count++;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

 */
