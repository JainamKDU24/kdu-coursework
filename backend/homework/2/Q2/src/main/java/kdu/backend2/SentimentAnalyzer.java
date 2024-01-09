package kdu.backend2;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SentimentAnalyzer{
    public static final Logger logger = LoggerFactory.getLogger(SentimentAnalyzer.class);
    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords, String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length];

        review = review.toLowerCase();

        for (int i = 0; i < featureSet.length; i++) {
            featureOpinions[i] = getOpinionOnFeature(review, featureSet[i], posOpinionWords, negOpinionWords);
        }

        return featureOpinions;
    }

    private static int getOpinionOnFeature(String review, String[] featureSynonyms, String[] posOpinionWords, String[] negOpinionWords) {
        int opinions = 0;

        for (String feature : featureSynonyms) {
            int wasPatternOpinion = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
            if (wasPatternOpinion != 0) {
                opinions = wasPatternOpinion;
                break;
            }

            int opinionFirstPattern = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
            if (opinionFirstPattern != 0) {
                opinions = opinionFirstPattern;
                break;
            }
        }

        return opinions;
    }

    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int opinions = 0;
        String pattern = feature + " was ";

        int index = review.indexOf(pattern);

        if (index != -1) {
            String subString = review.substring(index + pattern.length());

            for (String posOpinion : posOpinionWords) {
                if (subString.startsWith(posOpinion)) {
                    opinions = 1;
                    break;
                }
            }

            if (opinions != 1) {
                for (String negOpinion : negOpinionWords) {
                    if (subString.startsWith(negOpinion)) {
                        opinions = -1;
                        break;
                    }
                }
            }
        }

        return opinions;
    }

    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int opinions = 0;
        String[] sentences = review.split("\\.");

        for (String sentence : sentences) {
            sentence = sentence.trim();

            for (String pos : posOpinionWords) {
                if (sentence.contains(pos + " " + feature)) {
                    opinions = 1;
                    break;
                }
            }

            if (opinions != 1) {
                for (String neg : negOpinionWords) {
                    if (sentence.contains(neg + " " + feature)) {
                        opinions = -1;
                        break;
                    }
                }
            }

            if (opinions != 0) {
                break;
            }
        }

        return opinions;
    }

    public static void main(String[] args) {
        String review = "Haven't been here in years! Fantastic service and the food was delicious! " +
                "Definetly will be a frequent flyer! Francisco was very attentive";

        String[][] featureSet = {
                {"ambiance", "ambience", "atmosphere", "decor"},
                {"dessert", "ice cream", "desert"},
                {"food"},
                {"soup"},
                {"service", "management", "waiter", "waitress", "bartender", "staff", "server"}
        };

        String[] posOpinionWords = {"good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome", "delicious"};
        String[] negOpinionWords = {"slow", "bad", "horrible", "awful", "unprofessional", "poor"};

        int[] featureOpinions = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
        logger.info("Opinions on Features: " + Arrays.toString(featureOpinions));
    }
}
