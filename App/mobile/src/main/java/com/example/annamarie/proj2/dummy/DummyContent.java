package com.example.annamarie.proj2.dummy;

import android.media.Image;
import android.widget.ImageView;

import com.example.annamarie.proj2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    private static Integer[] reps = {
            R.drawable.dianne,
            R.drawable.bboxer,
            R.drawable.barbaralee

    };

    private static String[] names = {
            "Senator Dianne Feinstein",
            "Senator Barbara Boxer",
            "Representative Barbara Lee"

    };

    private static String[] parties = {
            "Democrat",
            "Democrat",
            "Democrat"

    };

    private static String[] addresses = {
            "http://www.feinstein.senate.gov/public/",
            "https://www.boxer.senate.gov/",
            "https://lee.house.gov/"

    };

    private static String[] tweets = {
            "The prospect of 4-4 decisions is not in the public interest. The Senate has an obligation to quickly consider the president's nominee.",
            "After watching the #GOPdebate I have only 3 words: Hillary for President! #ImWithHer",
            "I worked on this program as a young mom in #Oakland. Paved the way for today's school breakfasts. https://twitter.com/voxdotcom/status/698884442748751873"

    };

    private static String[] emails = {
            "senator@feinstein.senate.gov",
            "senator@boxer.senate.gov",
            "Barbara.Lee@mail.house.gov"

    };

    private static String[] tenures = {
            "1993-Present",
            " ",
            " "

    };

    private static String[] terms = {
            "January 3, 2019",
            " ",
            " "

    };
    private static String[] bills = {
            "S. 2568: California Desert Conservation, Off-Road Recreation, and Renewable Energy Act\n" +
                    "S. 2552: Interstate Threats Clarification Act of 2016\n" +
                    "S. 2533: California Long-Term Provisions for Water Supply and Short-Term Provisions for Emergency Drought Relief ...\n" +
                    "S. 2442: A bill to authorize the use of passenger facility charges at an airport ...\n" +
                    "S. 2422: Fiscal Year 2016 Department of Veterans Affairs Seismic Safety and Construction Authorization Act\n" +
                    "S. 2372: Requiring Reporting of Online Terrorist Activity Act\n" +
                    "S. 2337: Visa Waiver Program Security Enhancement Act",
            " ",
            " ",

    };

    private static String[] committees = {
            "Vice Chairman, Senate Select Committee on Intelligence\n" +
                    "Senate Committee on Appropriations\n" +
                    "Ranking Member, Subcommittee on Energy and Water Development\n" +
                    "Member, Subcommittee on Agriculture, Rural Development, Food and Drug Administration, and Related Agencies\n" +
                    "Member, Subcommittee on Commerce, Justice, Science, and Related Agencies\n" +
                    "Member, Subcommittee on Department of Defense\n" +
                    "Member, Subcommittee on Department of the Interior, Environment, and Related Agencies\n" +
                    "Member, Subcommittee on Transportation, Housing and Urban Development, and Related Agencies\n" +
                    "Senate Committee on the Judiciary\n" +
                    "Member, Subcommittee on Immigration and the National Interest\n" +
                    "Member, Subcommittee on Oversight, Agency Action, Federal Rights and Federal Courts\n" +
                    "Member, Subcommittee on Privacy, Technology and the Law\n" +
                    "Senate Committee on Rules and Administration\n" +
                    "United States Senate Caucus on International Narcotics Control",
            " ",
            " ",

    };
    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();


    static {
        // Add some sample items.
        for (int i = 0; i <= 2; i++) {
            addItem(createDummyItem(i, names[i], parties[i], addresses[i], tweets[i], emails[i], reps[i], bills[i], committees[i], tenures[i], terms[i]));

        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position, String name, String party, String address, String tweet, String email, int image, String bill, String c, String tenures, String term) {
        return new DummyItem(String.valueOf(position), name, party, address, tweet, email, image, bill, c, tenures, term);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String name;
        public final String party;
        public final String web_address;
        public final String tweet;
        public final String email;
        public final int image;
        public final String bills;
        public final String committees;
        public final String tenure;
        public final String term;


        public DummyItem(String id, String name, String party, String address, String tweet, String email, int image, String bills, String c, String tenure, String term) {
            this.id = id;
            this.name = name;
            this.party = party;
            this.web_address = address;
            this.tweet = tweet;
            this.email = email;
            this.image = image;
            this.bills = bills;
            this.committees = c;
            this.tenure = tenure;
            this.term = term;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
