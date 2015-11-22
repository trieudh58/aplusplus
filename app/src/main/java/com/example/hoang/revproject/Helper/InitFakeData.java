package com.example.hoang.revproject.Helper;

import android.content.Context;

import com.example.hoang.revproject.Model.AlarmDBHelper;
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

        dbHelper.createVocab(model);
        model.setDone(1);
        dbHelper.updateVocab(model);
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
    }

}
