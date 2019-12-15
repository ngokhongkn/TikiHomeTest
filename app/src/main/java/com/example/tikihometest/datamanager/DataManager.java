package com.example.tikihometest.datamanager;

import com.example.tikihometest.model.HotKeywords;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {

    @Inject
    public DataManager() {
    }

    public HotKeywords parseStringJsonToObjectAndEdit(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("keywords", jsonArray);
            Gson gson = new Gson();
            HotKeywords hotKeywords = gson.fromJson(jsonObject.toString(), HotKeywords.class);
            return editData(hotKeywords);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new HotKeywords(new ArrayList());
    }

    /*
        If the keyword is more than one word, then display in two lines
        These two lines should have minimum difference in length
     */
    private HotKeywords editData(HotKeywords data) {
        int dataSize = data.getKeywords().size();
        for (int i = 0; i < dataSize; i++) {
            String keyword = data.getKeywords().get(i);
            String[] arrKeyword = keyword.split(" ");
            /*If keyword is more than one word, we will split keyword*/
            switch (arrKeyword.length) {
                case 1:
                    break;
                case 2:
                    data.getKeywords().set(i, arrKeyword[0] + "\n" + arrKeyword[1]);
                    break;
                default:
                    char[] chars = keyword.toCharArray();
                    int charsLength = chars.length;

                    int preEndSpaceLineOne = -1;
                    int endSpaceLineOne = 0;

                    for (int j = 0; j < charsLength; j++) {
                        if (chars[j] == ' ') {
                            if (j >= charsLength / 2) {
                                endSpaceLineOne = j;
                                break;
                            }
                            preEndSpaceLineOne = j;
                        }

                    }

                    int diffBetweenTwoLineWithPreEndSpace = Math.abs(preEndSpaceLineOne - (charsLength - preEndSpaceLineOne));
                    int diffBetweenTwoLineWithEndSpace = Math.abs(endSpaceLineOne - (charsLength - endSpaceLineOne));

                    /*We will choose the one with the lower difference*/
                    if (diffBetweenTwoLineWithPreEndSpace < diffBetweenTwoLineWithEndSpace) {
                        chars[preEndSpaceLineOne] = '\n';
                    } else {
                        chars[endSpaceLineOne] = '\n';
                    }

                    data.getKeywords().set(i, String.valueOf(chars));
                    break;
            }

        }
        return data;
    }
}
