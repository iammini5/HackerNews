package com.yzeng.hackernews.test.dagger.utils;

import com.google.gson.Gson;
import com.hackernews.api.Item;

public class Utils {

    static public final String STORY_STR_COMMENTS = "{\n" +
            "  \"by\" : \"wtracy\",\n" +
            "  \"descendants\" : 35,\n" +
            "  \"id\" : 14209685,\n" +
            "  \"kids\" : [ 14210154, 14209897, 14209852, 14209895, 14210067, 14210037, 14209933, 14209871, 14210025, 14209892 ],\n" +
            "  \"score\" : 45,\n" +
            "  \"time\" : 1493275927,\n" +
            "  \"title\" : \"Ada: a C Developer's Perspective\",\n" +
            "  \"type\" : \"story\",\n" +
            "  \"url\" : \"http://www.methodsandtools.com/archive/ada.php\"\n" +
            "}";

    static public final String STORY_STR_NO_COMMENTS = "{\n" +
            "  \"by\" : \"wtracy\",\n" +
            "  \"descendants\" : 35,\n" +
            "  \"id\" : 14209685,\n" +
            "  \"score\" : 45,\n" +
            "  \"time\" : 1493275927,\n" +
            "  \"title\" : \"Ada: a C Developer's Perspective\",\n" +
            "  \"type\" : \"story\",\n" +
            "  \"url\" : \"http://www.methodsandtools.com/archive/ada.php\"\n" +
            "}";

    static public final String COMMENT_STR = "{\n" +
            "  \"by\" : \"ewanm89\",\n" +
            "  \"id\" : 14210154,\n" +
                "  \"kids\" : [ 14210200, 14210176 ],\n" +
            "  \"parent\" : 14209685,\n" +
            "  \"text\" : \"Here is my list of shortcomings when it comes to Ada<p>1) Ada is not a magic bullet, the seminal case study of why computer bugs are bad taught in CS courses around the world is the failed 1996 Ariane 5 rocket launch where the rocket veered off course and then exploded due to a software error that caused it not to understand the data it was getting from a sensor. More recently there are plenty of demonstrations of poor security in commercial plane avionics systems.<p>2) Ada is not easy to write, it&#x27;s around the level of C I grant you, but it is not easy by any means, more importantly it is complex not simple. Each of those &quot;features&quot; like assert adds a layer of complexity to the system making it far harder for the programmer to write for. I think we all agree if we could program with simple functions that each do one thing and one thing well then combine those functions makes for a much simpler language and easier to think about.<p>3) Overhead, each of these features like assert adds a lot of overhead into the system, this is just not viable in a lot of systems, whats more those features are optional, so it is the first thing to drop where possible.<p>4) Toolchain and library support, most of us can&#x27;t afford expensive toolchains and libraries, we don&#x27;t work for very rich government contractors. Also we need libraries and toolchains are well maintained. Java has a massive standard library and a lot of free 3rd party libraries available, C&#x2F;C++, python, perl, ruby, rust, javascript, c#, clojure... are all similar in that they have well maintained tool chains and good library support freely available either directly by language authors or from a third party.\",\n" +
            "  \"time\" : 1493283850,\n" +
            "  \"type\" : \"comment\"\n" +
            "}";

    static public final String REPLY_STR_1 = "{\n" +
            "  \"by\" : \"coldtea\",\n" +
            "  \"id\" : 14210200,\n" +
            "  \"kids\" : [ 14210244 ],\n" +
            "  \"parent\" : 14210154,\n" +
            "  \"text\" : \"&gt;<i>I think we all agree if we could program with simple functions that each do one thing and one thing well then combine those functions makes for a much simpler language and easier to think about.</i><p>Well, I don&#x27;t agree. You could end up with tons of small functions, and complex interactions between them (whether they are pure or not) than make for spaghetti logic.<p>I also don&#x27;t see how Ada&#x27;s pre&#x2F;post conditions make it more difficult to program. More tedious, maybe, but about as much as writing tests. Not more difficult as in conceptually more challenging.<p>As for the Ariane error, Ada could have prevented it, if it was left to -- but the programmers didn&#x27;t let it. It&#x27;s not a silver bullet of course, but nothing is, not even formally proven programs.<p>Finally, the overhead is true, but some domains don&#x27;t need much speed anyway, and for others, you could always have the checks for all demo&#x2F;dev runs and drop them for the final production output.\",\n" +
            "  \"time\" : 1493284658,\n" +
            "  \"type\" : \"comment\"\n" +
            "}";

    static public final String REPLY_STR_2 = "{\n" +
            "  \"by\" : \"sgift\",\n" +
            "  \"id\" : 14210176,\n" +
            "  \"kids\" : [ 14210297 ],\n" +
            "  \"parent\" : 14210154,\n" +
            "  \"text\" : \"1) and 3) are related as far as I remember - Ada WOULD have found the problem which was responsible for the Ariane 5 launch failure, but the section involved was speed critical, so the asserts were dropped to make the code run fast enough.<p>Sad reality: The best security features in the world are useless if they are too slow for your use case.\",\n" +
            "  \"time\" : 1493284194,\n" +
            "  \"type\" : \"comment\"\n" +
            "}";

    static public final String LIST_STR = "[14209685,14209874,14208324,14207752,14209090,14208121,14206635,14210117,14207911,14204939,14205042,14209119,14204960,14204702,14204759,14208884,14181210,14205997,14206309,14205682,14201562,14201894,14206331,14209168,14207107,14205917,14205361,14206962,14208227,14202994,14205247,14208290,14205953,14201908,14201822,14203141,14202456,14202488,14202520,14206175,14209882,14206371,14204469,14202279,14196077,14200563,14201813,14196731,14202632,14202217,14207730,14202121,14195664,14202736,14201782,14196812,14202044,14203812,14206048,14200446,14202145,14206964,14201295,14196322,14192353,14202124,14203594,14191577,14209155,14202598,14189392,14201207,14182262,14202375,14202058,14202421,14204878,14202367,14209312,14192383,14199028,14189688,14206866,14202585,14194422,14203010,14206906,14203077,14208192,14202095,14201876,14195956,14196708,14209141,14204773,14199125,14207604,14198261,14209037,14207787,14190069,14208455,14191681,14184528,14189351,14202557,14195522,14190937,14200486,14203096,14205697,14190774,14202619,14182217,14194026,14198557,14196935,14204838,14199299,14199197,14192442,14192817,14190380,14206968,14191186,14198866,14191333,14187979,14190736,14204436,14194570,14196339,14199364,14199607,14178416,14197013,14189528,14192946,14207842,14192230,14198403,14206417,14181612,14191161,14198229,14198516,14201670,14193488,14197852,14194361,14198470,14206814,14202324,14192425,14178399,14186699,14191271,14191085,14202164,14190648,14198734,14204593,14190334,14194681,14188953,14183767,14203460,14180034,14189087,14180673,14206118,14191542,14192378,14199288,14190365,14185891,14205432,14183597,14206462,14193503,14191080,14203547,14195496,14206221,14190335,14180181,14192503,14190281,14190695,14204923,14181662,14192097,14186817,14186450,14196325,14183242,14199038,14207042,14179783,14185314,14193091,14193748,14182203,14198689,14183075,14187026,14182296,14194954,14197512,14198133,14193954,14189664,14180233,14196154,14189896,14188120,14203473,14183543,14182318,14194502,14183612,14204860,14197456,14180497,14200122,14185587,14197329,14193115,14206262,14185121,14194086,14193232,14192514,14196064,14188638,14198487,14185236,14181989,14205485,14194845,14191560,14193543,14185818,14180017,14181152,14204871,14186150,14182338,14180996,14184499,14191127,14187191,14179599,14195838,14181417,14188468,14188759,14183219,14198635,14196564,14197270,14203459,14182393,14183644,14189341,14188342,14180755,14190040,14184082,14183099,14200132,14187769,14187557,14190439,14192566,14185923,14186545,14183039,14194166,14189251,14186329,14183147,14182234,14194992,14191871,14178918,14179990,14182406,14195159,14192974,14178397,14192545,14194803,14191369,14192216,14179538,14197569,14198061,14196263,14179006,14187783,14181525,14180524,14191568,14184875,14197230,14190497,14198991,14188750,14200124,14180761,14178474,14191702,14189814,14191043,14183772,14181302,14200925,14194756,14200509,14199455,14179399,14197077,14182184,14183044,14199041,14178429,14198795,14198598,14196080,14185254,14188926,14196603,14200289,14178424,14191878,14181051,14195172,14183986,14199934,14188516,14182892,14179534,14196680,14196560,14186874,14183121,14198792,14180860,14186352,14178621,14184183,14197946,14197654,14192938,14179110,14190492,14183266,14190240,14187416,14190117,14186825,14184043,14194938,14184277,14189197,14183179,14191732,14193698,14179879,14198938,14182377,14180917,14194112,14184715,14185058,14194273,14191473,14178411,14180614,14180464,14183057,14180881,14199758,14180169,14189496,14183447,14178868,14189058,14183070,14180874,14182788,14193181,14180687,14180664,14187174,14192639,14186030,14178976,14178629,14194353,14181016,14180914,14180513,14182959,14182633,14189794,14178947,14182200,14192644,14180909,14180767,14180753,14180268,14183686,14184157,14179081,14183328,14181447,14185531,14198575,14184744,14192696,14203617,14198308,14188212,14200256,14181561,14184008,14189992,14182503,14186786,14183699,14186211,14186456,14194084,14198372,14185905,14192866,14186986,14189616,14183786,14188075,14190764]";

    static private final Gson GSON = new Gson();
    static public final Item getItemStoryWithComments(){
        Item item = GSON.fromJson(STORY_STR_COMMENTS, Item.class);
        return item;
    }
    static public final Item getItemStoryWithoutComments(){
        Item item = GSON.fromJson(STORY_STR_NO_COMMENTS, Item.class);
        return item;
    }
    static public final Item getItemCommentDefault(){
        Item item = GSON.fromJson(COMMENT_STR, Item.class);
        return item;
    }

    static public final Item getItemReplyDefault1(){
        Item item = GSON.fromJson(REPLY_STR_1, Item.class);
        return item;
    }

    static public final Item getItemReplyDefault2(){
        Item item = GSON.fromJson(REPLY_STR_2, Item.class);
        return item;
    }
}
