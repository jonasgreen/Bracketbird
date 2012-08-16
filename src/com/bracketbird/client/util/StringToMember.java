package com.bracketbird.client.util;

import com.bracketbird.client.model.*;

import java.util.*;

/**
 *
 */
public class StringToMember {
    private Map<String, Member> stringToMemberMap = new TreeMap<String, Member>();


    public void add(Collection<Member> members) {
        for (Member m : members) {
            stringToMemberMap.put(genrateUnikeString(m), m);
        }
    }

    private String genrateUnikeString(Member m) {
        StringBuffer sb = new StringBuffer();
        m.appendLongName(sb);
        if (stringToMemberMap.get(sb.toString()) != null) {
            appendEmptyness(sb);
        }
        return sb.toString();
    }

    private void appendEmptyness(StringBuffer sb) {
        while (stringToMemberMap.get(sb.toString()) != null) {
            sb.append(" ");
        }
    }

    public void add(Member m) {
        stringToMemberMap.put(genrateUnikeString(m), m);
    }

    public Map<String, Member> getStringToMemberMap() {
        return stringToMemberMap;
    }
}
