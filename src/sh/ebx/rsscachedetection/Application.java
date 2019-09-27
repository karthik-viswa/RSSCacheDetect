/**
 * ***********************************************************************
 *
 * ECHOBOX CONFIDENTIAL
 *
 * All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of Echobox Ltd. and its
 * suppliers, if any. The intellectual and technical concepts contained herein are proprietary to
 * Echobox Ltd. and its suppliers and may be covered by Patents, patents in process, and are
 * protected by trade secret or copyright law. Dissemination of this information or reproduction of
 * this material, in any format, is strictly forbidden unless prior written permission is obtained
 * from Echobox Ltd.
 */
package sh.ebx.rsscachedetection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A skeleton application entry point that can be used to load all of the test data.
 * @author MarcF
 */
public class Application {

  /**
   * Main entry point into application
   * @param args
   */
  public static void main(String[] args) throws Exception {
    
    //Load all of the test data
    List<RSSTestData> allTestData = getAllTestData();
    
    /* Challenge code starts here, Good luck! */

    List<RSSFeedCacheEstimate> cacheEstimates = allTestData.stream()
                                                .map(RSSFeedCacheEstimator::new)
                                                .map(RSSFeedCacheEstimator::getCacheEstimate)
                                                .collect(Collectors.toList());

    writeResultsToFile(cacheEstimates);
  }

  /**
   * Load the test data required to validate the RSS cache detection tool
   * @return The loaded test data
   * @throws IOException If an error occurs while trying to load the test data
   */
  public static List<RSSTestData> getAllTestData() throws IOException {

    // The data directories we will load data from
    File[] dataDirs = new File[] { new File("ACCOUNT_ARTICLE_SOURCES/NORMAL"),
        new File("ACCOUNT_ARTICLE_SOURCES/BAD_PUBLISH_TIME")};

    // The list that we will return after loading data
    List<RSSTestData> loadedData = new ArrayList<>();

    // Parse each file within each data directory
    for (int i = 0; i < dataDirs.length; i++) {

      for (File file : dataDirs[i].listFiles()) {
        //System.out.println("file:" + file.getAbsolutePath());
        // Parse the feed id from the filename
        RSSFeedId rssFeedId = 
            new RSSFeedId(Long.parseLong(file.getName().split("\\.")[0].split("_")[3]));
        // The list of published articles that will be loaded from this data file
        List<AccountArticleSource> rssFeedData = new ArrayList<>();

        // Read in each line of the CSV and parse it into an account article source
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

          // Skip the first line as it contains the headers
          br.readLine();
          for (String line; (line = br.readLine()) != null;) {
            String[] split = line.split(",");
            // Split the line data, format is
            // [0]-account_article_source_id
            // [1]-time_published
            // [2]-time_created
            AccountArticleSourceId id = new AccountArticleSourceId(Long.parseLong(split[0]));
            Long timePublished = Long.parseLong(split[1]);
            Long timeCreated = Long.parseLong(split[2]);
            // We use defaults or null for any of the irrelevant fields
            AccountArticleSource aas = new AccountArticleSource(id, rssFeedId, timePublished, 
                timeCreated);
            rssFeedData.add(aas);
          }
        }

        // Add the completed data
        switch (i) {
          case 0:
            loadedData.add(new RSSTestData(rssFeedId, RSSType.NORMAL, rssFeedData));
            break;
          case 1:
            loadedData.add(new RSSTestData(rssFeedId, RSSType.BAD_PUBLISH_TIME, rssFeedData));
            break;
          default:
            throw new IllegalStateException("Unexpected RSS type index.");
        }
      }
    }

    System.out.println("loadedData:" + loadedData.size());

    return loadedData;
  }

  private static void writeResultsToFile(List<RSSFeedCacheEstimate> cacheEstimates) {
    File csvOutputFile = new File("CACHE_ESTIMATES.csv");

    String header = "FeedIdentifier,Caching?,CachingInterval";

    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
        pw.println(header);
        cacheEstimates.stream()
                      .map(RSSFeedCacheEstimate::getAsCsv)
                      .forEach(pw::println);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("Could not find the file to write the results to");
    }
  }

}
