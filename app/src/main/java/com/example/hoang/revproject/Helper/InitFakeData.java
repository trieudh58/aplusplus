package com.example.hoang.revproject.Helper;

import android.content.Context;

import com.example.hoang.revproject.Model.AlarmDBHelper;
import com.example.hoang.revproject.Model.ListeningModel;
import com.example.hoang.revproject.Model.VocabularyModel;

/**
 * Created by hoang on 11/18/2015.
 */
public class InitFakeData {

    private AlarmDBHelper dbHelper;
    private Context mContext;
    private static final String ART = "art";
    private static final String BABY = "baby";
    private static final String BUSINESS = "business";
    private static final String CITY = "city";
    private static final String COMPUTER = "computer";

    public InitFakeData(Context mContext){
        this.mContext = mContext;
        dbHelper = new AlarmDBHelper(mContext);
    }

    public void initData(){
        VocabularyModel model = new VocabularyModel("Actor", "/'æktər/", " Danh từ \n" +
                "Diễn viên (kịch, tuồng, chèo, (điện ảnh)...); kép, kép hát\n" +
                "\n" +
                "        film actor \n" +
                "        tài tử đóng phim, diễn viên điện ảnh \n" +
                "\n" +
                "(từ hiếm,nghĩa hiếm) người làm (một việc gì)\n" +
                "\n" +
                "        a bad actor \n" +
                "        (từ Mỹ,nghĩa Mỹ) người khó tin cậy \n" +
                "\n" +
                "Chuyên ngành\n" +
                "Hóa học & vật liệu\n" +
                "chất đồng tác\n" +
                "Vật lý\n" +
                "cấu tác động\n" +
                "cấu thực hiện\n" +
                "Kỹ thuật chung\n" +
                "người hành động", "@drawable/i01", "actor", ART );

        VocabularyModel model1 = new VocabularyModel("Actress", "/'æktrɪs/", "Danh từ\n" +
                "\n" +
                "\n" +
                " Nữ diễn viên, đào hát", "@drawable/i02", "actress", ART);

        VocabularyModel model2 = new VocabularyModel("Audience", "/ˈɔː.di.ənts/", "danh từ \n" +
                " những người nghe, thính giả; người xem, khán giả; bạn đọc, độc giả \n" +
                " sự nghe \n" +
                "to give audience+ lắng nghe \n" +
                " sự hội kiến, sự yết kiến; sự tiếp kiến \n" +
                "to grant an audience to somebody+ tiếp kiến ai ", "@drawable/i03", "audient", ART );

        VocabularyModel model3 = new VocabularyModel("Baby bottle", "/ˈbeɪ.bi ˈbɒt.ļ/", "danh từ \n" +
                " chai, lọ \n" +
                "bottled fruit+ quả đóng chai \n" +
                " bầu sữa (cho trẻ em bú) ((cũng) feeding) \n" +
                "to be brought up on the bottle+ nuôi bằng sữa ngoài, không cho bú mẹ ", "@drawable/i23", "babybottle", BABY);

        VocabularyModel model4 = new VocabularyModel("Carriage ", "/ˈkær.ɪdʒ/", "danh từ \n" +
                " Xe day cho em be \n" +
                "a carriage and pair+ xe hai ngựa \n" +
                "a carriage and four+ xe bốn ngựa \n" +
                " (ngành đường sắt) toa hành khách \n" +
                "the first class carriages+ các toa hạng nhất ", "@drawable/i72", "carriage", BABY);

        VocabularyModel model5 = new VocabularyModel("Crib ", "/krɪb/", "danh từ \n" +
                " giường cũi (của trẻ con) \n" +
                " lều, nhà nhỏ; nhà ở \n" +
                " máng ăn (cho súc vật) \n" +
                "(ngôn ngữ nhà trường) bài dịch để quay cóp \n" +
                " (thông tục) sự ăn cắp văn \n" +
                " (từ Mỹ,nghĩa Mỹ) thùng (đựng muối", "@drawable/i50", "crib", BABY );

        VocabularyModel model6 = new VocabularyModel("Building  ", "/ˈbɪldɪŋ/", "danh từ \n" +
                " kiến trúc; sự xây dựng \n" +
                " công trình kiến trúc, công trình xây dựng \n" +
                " toà nhà, binđinh ", "@drawable/i20", "building", CITY );

        VocabularyModel model7 = new VocabularyModel("Skyscraper  ", "/ˈskaɪˌskreɪpə/", "danh từ \n" +
                " như skysail \n" +
                " nhà chọc trời (toà nhà hiện đại rất cao ở thành thị) ", "@drawable/i69", "skyscraper", CITY );

        VocabularyModel model8 = new VocabularyModel("Cable  ", "/ˈkeɪ.bļ/", "danh từ \n" +
                " dây cáp \n" +
                " cáp xuyên đại dương \n" +
                " (như) cablegram \n" +
                " (hàng hải) dây neo \n" +
                " (hàng hải) tầm (1 qoành 0 hải lý tức 183m, (từ Mỹ,nghĩa Mỹ) 219m) ((cũng) cable length) \n" +
                " đường viền xoắn (quanh cột); đường chạm xoắn (vòng vàng) \n" +
                " (từ lóng) chết ngoẻo \n" +
                " động từ \n" +
                " cột bằng dây cáp, buộc bằng dây cáp \n" +
                " đánh điện xuyên đại dương; đánh cáp \n" +
                " trang bị bằng đường viền xoắn (cột) \n" +
                " Default_cw\n" +
                " (Tech) cáp, dây cáp [điện] \n" +
                " Default_cw\n" +
                " cơ, dây cáp \n" +
                " suspension c. dây cáp treo ", "@drawable/i43", "cable", COMPUTER );

        VocabularyModel model9 = new VocabularyModel("Laptop  ", "/læp tɒp/", "danh từ \n" +
                " máy tính laptop, máy tính xách tay  ", "@drawable/i62", "laptop", COMPUTER );

        VocabularyModel model10 = new VocabularyModel("Desk  ", "/dɛsk/", "danh từ \n" +
                " bàn học sinh, bàn viết, bàn làm việc \n" +
                " (the desk) công việc văn phòng, công tác văn thư; nghiệp bút nghiên \n" +
                " (từ Mỹ,nghĩa Mỹ) giá để kinh; giá để bản nhạc (ở nhà thờ); bục giảng kinh \n" +
                " nơi thu tiền \n" +
                " (từ Mỹ,nghĩa Mỹ) toà soạn (báo) \n" +
                " (từ Mỹ,nghĩa Mỹ) tổ (phụ trách nghiên cứu chuyên đề trong một phòng) \n" +
                "the Korean desk of the Asian Department+ tổ (phụ trách vấn đề) Triều-tiên của Vụ Châu-á \n" +
                " Default_cw\n" +
                " (máy tính) bàn; chỗ để bìa đục lỗ \n" +
                " control d. bàn điều khiển \n" +
                " test d. bàn thử, bảng thử ", "@drawable/i26", "desk", BUSINESS );

        ListeningModel listeningModel = new ListeningModel("Melting Glaciers And Climate Talks", "Terrorist attacks in Paris are not expected to stop the United Nations from hosting a major climate conference in that city.\n" +
                "\n" +
                "Next week nearly 120 heads of state and government will gather in the City of Lights to discuss ways to cut harmful pollution.\n" +
                "\n" +
                "President Barack Obama, China's Xi Jinping and Russia's Vladimir Putin are scheduled to attend. They will try to reach an agreement to fight changes in the Earth’s climate.\n" +
                "\n" +
                "The meeting is known as COP21. The U.N. says it wants an agreement among nations to limit the rise in world temperatures to 2 degrees Celsius.\n" +
                "\n" +
                "Before the terrorist attacks, France hosted a pre-summit meeting. France's Laurent Fabius told journalists that “the task ahead is considerable.”\n" +
                "\n" +
                "Governments are discussing how to lower harmful gas released from burning fossil fuels, like oil and gasoline. These have been blamed for climate change.\n" +
                "\n" +
                "Experts warned recently that a major glacier in Greenland is quickly melting and falling into the Atlantic Ocean.\n" +
                "\n" +
                "If the entire glacier in the northeast of Greenland melts and falls, global sea levels could rise by a half meter.\n" +
                "\n" +
                "The study, published in the journal Science, said the glacier melted three times faster than earlier. Known as Zachariae Isstrom, the glacier is losing 5 billion tons of mass per year, according to the study.\n" +
                "\n" +
                "The glacier is dumping many icebergs into the ocean. That will raise sea levels in future decades, Jeremie Mouginot wrote. He is the study’s lead author and a professor at the University of California-Irvine.\n" +
                "\n" +
                "The study based its findings on 40 years of data from satellite and aerial surveys. They looked at the shape, size and position of glacial ice. \n" +
                "\n" +
                "Warmer ocean water is eroding the glacier from below. Warmer air temperatures are melting it from above. Another Greenland glacier is also melting, but not as quickly because it is in a protected location.\n" +
                "\n" +
                "The study said the two glaciers make up 12 percent of the Greenland ice sheet. It said if they fully collapse, that would increase global sea levels by more than one meter.\n" +
                "\n" +
                "Greenland is near the icy North Pole of the planet.\n" +
                "\n" +
                "However, another recent study said that ice in Antarctica at the South Pole is growing.\n" +
                "\n" +
                "In the Journal of Glaciology, NASA said satellites showed the Antarctic ice sheet gained 112 billion tons of ice from 1992 to 2001. The gain slowed to 82 billion tons from 2003 to 2008.\n" +
                "\n" +
                "That is what Jay Zwally said. He is a glaciologist with the NASA Goddard Space Flight Center in Greenbelt, Maryland. He is the study’s lead author.\n" +
                "\n" +
                "Critics question the study. Many other studies have found that Antarctica is generally losing ice.\n" +
                "\n" +
                "Zwally said he agrees with others that ice is melting in the Antarctic Peninsula and West Antarctica.\n" +
                "\n" +
                "But, he says that East Antarctica and other parts are gaining ice.\n" +
                "\n" +
                "“There, we see an ice gain that exceeds the losses in the other areas,” he said.\n" +
                "\n" +
                "Zwally and his team used satellites to measure changes over large and small areas.\n" +
                "\n" +
                "Snowfall is not common in Antarctica because it is technically a desert. Antarctica is a dry desert, with cold, brutal weather.\n" +
                "\n" +
                "I’m Anne Ball.", "@drawable/bai1", "bai1");

        ListeningModel listeningMode2 = new ListeningModel("China Aid Inflames Vietnamese Lawmakers", "Vietnamese lawmakers say they worry about becoming too dependent on China for loans and credit.\n" +
                "\n" +
                "On national TV on Tuesday, a lawmaker said he worries that Vietnam’s independence will be threatened by money and investment from China.\n" +
                "\n" +
                "It is unusual for Vietnamese officials to confront Prime Minister Nguyen Tan Dung in public.\n" +
                "\n" +
                "China recently offered $200-million in credit over five years to one of Vietnam’s largest banks. China is also a major investor in a $400-million elevated railway project in Hanoi.\n" +
                "\n" +
                "Another lawmaker wanted to know how Vietnam will respond to China’s moves to acquire land in the South China Sea.\n" +
                "\n" +
                "The U.S. has offered Vietnam $40-million in aid for security in the South China Sea. This funding comes as China tries to expand its sea territory.\n" +
                "\n" +
                "Vietnam and the Philippines also signed a deal to cooperate in relation to the South China Sea.\n" +
                "\n" +
                "I’m Jonathan Evans.", "@drawable/bai2", "bai2");

        ListeningModel listeningMode3 = new ListeningModel("The History of the Islamic State Terrorist Group", "Who and what is the terrorist group that carried out deadly attacks last week in Paris?\n" +
                "\n" +
                "They call themselves the Islamic State of Iraq and the Levant, or ISIS. They also are called IS and ISIL. Critics call them Daesh, a word Islamic State does not prefer to be called.\n" +
                "\n" +
                "Islamic State has been active in Iraq and Syria since 2013. They say they follow a fundamentalist or extreme form of Islam. That fundamentalism is in response to a liberal way of life in the West, they say.\n" +
                "\n" +
                "They are known for mass killings, kidnappings and beheadings of local people as well as Westerners. They have set people on fire alive and blown others up. They have crucified others.\n" +
                "\n" +
                "Earlier this month, ISIS claimed responsibility for bombing a Russian passenger plane in Egypt. All 224 people on the plane were killed.\n" +
                "\n" +
                "Last week, it claimed responsibility for two explosions in the Lebanese capital, Beirut. At least 41 people were killed.\n" +
                "\n" +
                "And the group said it carried out attacks in Paris on Friday. More than 130 people were killed.\n" +
                "\n" +
                "Experts and eye-witnesses describe their brutality as the worse ever seen.\n" +
                "\n" +
                "The group was formed after U.S. forces invaded Iraq in 2003. A militant leader from Jordan named Abu Musab al-Zarqawi joined the terrorist group al-Qaida. They called themselves “al-Qaida in Iraq.”\n" +
                "\n" +
                "But Zarqawi was killed in a U.S. airstrike in 2003.\n" +
                "\n" +
                "Abu Bakr al-Baghdadi has led ISIS since 2010. He had been a prisoner of U.S. forces in Iraq. He and other prisoners joined IS after they were released. Other members of Islamic State come from the political party that supported Iraqi dictator Saddam Hussein. He was ousted and later executed.\n" +
                "\n" +
                "In 2013, Islamic State expanded into the civil war in Syria. By June 2014, IS took control of many cities and towns in Iraq and Syria. It announced it had created a “caliphate,” or a political-religious government for Muslims.\n" +
                "\n" +
                "All Muslims must reject the governments of any other country. It says other governments are signs of opposition to Islam, the religion of Muslims.\n" +
                "\n" +
                "After the caliphate was announced, Islamic State called al-Baghdadi the “leader for Muslims everywhere.” This angered most Muslims who did not agree with Islamic State.\n" +
                "\n" +
                "Analysts say Islamic State earns money through the illegal sale of oil in Syria, and by kidnappings, extortion and other crimes. It also receives money from supporters in some Persian Gulf nations.\n" +
                "\n" +
                "U.S. government officials estimate that Islamic State may have earned as much as $100 million in 2014 from the sale of oil. The U.S. Treasury Department says the group sold the oil to local businessmen. It says they illegally transported it to Turkey and Iran, or sold it to the Syrian government.\n" +
                "\n" +
                "Experts say air strikes on oil fields and refineries have reduced Islamic State earnings.\n" +
                "\n" +
                "Islamic State follows an extreme version of Sunni Islam, analysts say. They are the only true believers of Islam, they say, and believe others seek to destroy Islam. This justifies why they attack Muslims and non-Muslims.\n" +
                "\n" +
                "The Islamic State has also forced women into sexual slavery. It says its holy book -- the Quran -- supports the enslavement of women and girls.\n" +
                "\n" +
                "Experts believe more than 3,000 Yazidi women are held by Islamic State. Some Yazidi women have escaped or were rescued. They say they were traded among fighters and given to Islamic State leaders as rewards.\n" +
                "\n" +
                "Islamic State uses social media to lure young people to support its goals and join the group as fighters.\n" +
                "\n" +
                "I’m Jim Tedder.", "@drawable/bai3", "bai3");

        ListeningModel listeningMode4 = new ListeningModel("President Obama Calls for Lower Tensions", "U.S. President Barack Obama said “bold measures” are needed to ease growing tensions over the South China Sea.\n" +
                "\n" +
                "Six governments with shores on the South China Sea say they control parts of it.\n" +
                "\n" +
                "President Obama made the comments before the Asia-Pacific Economic Cooperation (APEC) leaders’ summit in Manila.\n" +
                "\n" +
                "He said, “we agree on the need for bold steps to lower tensions, including pledging to halt further reclamation, new construction and militarization of disputed areas in the South China Sea.”\n" +
                "\n" +
                "China is dredging the sea floor and forming “mini islands.” Two of the new islands have airstrips and harbors for military air and sea craft.\n" +
                "\n" +
                "Neighboring countries are alarmed that China has not talked openly about its plans for the islands.\n" +
                "\n" +
                "China said there is no need for outsiders to get involved in the dispute among the Asian countries.\n" +
                "\n" +
                "Brunei, Malaysia, Taiwan and Vietnam all have claims to the sea.\n" +
                "\n" +
                "Obama said the U.S. will stay neutral in the dispute.\n" +
                "\n" +
                "The Philippines signed an agreement last year that gives the U.S rights to place military equipment at strategic locations in the country.\n" +
                "\n" +
                "Philippine President Benigno Aquino said the presence of the U.S. in the Philippines should help defuse tension in the region.\n" +
                "\n" +
                "On Tuesday, President Obama pledged $250-million in military contributions for Asian countries worried about China’s activity in the sea.\n" +
                "\n" +
                "The Philippines also entered an official maritime partnership with Vietnam.\n" +
                "\n" +
                "I’m Mario Ritter.", "@drawable/bai4", "bai4");

        dbHelper.createVocab(model);
        dbHelper.createVocab(model1);
        dbHelper.createVocab(model2);
        dbHelper.createVocab(model3);
        dbHelper.createVocab(model4);
        dbHelper.createVocab(model5);
        dbHelper.createVocab(model6);
        dbHelper.createVocab(model7);
        dbHelper.createVocab(model8);
        dbHelper.createVocab(model9);
        dbHelper.createVocab(model10);
        dbHelper.createListening(listeningModel);
        dbHelper.createListening(listeningMode2);
        dbHelper.createListening(listeningMode3);
        dbHelper.createListening(listeningMode4);
    }

}
