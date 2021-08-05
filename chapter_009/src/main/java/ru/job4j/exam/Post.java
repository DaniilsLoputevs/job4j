package ru.job4j.exam;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Post {
    /**
     * String      - key    - userName.
     * Set<String> - value  - userEmailList.
     */
    HashMap<String, Set<String>> result = new HashMap<>();
    /**
     * String - key    - email
     * String - value  - userName
     */
    HashMap<String, String> emailResultContains = new HashMap<>();

    /**
     * @param userAndEmailsMap -
     * @return Map -
     * String      - key    - userName.
     * Set<String> - value  - userEmailList.
     */
    Map<String, Set<String>> sotUsers(Map<String, Set<String>> userAndEmailsMap) {
        for (var mapEntry : userAndEmailsMap.entrySet()) {
            var emails = mapEntry.getValue();
            var groupName = mapEntry.getKey();
            boolean check = false;

            for (var email : emails) {
                if (emailResultContains.containsKey(email)) {
                    groupName = emailResultContains.get(email);
                    check = true;
                }
            }
            var finalGroupName = groupName;
            if (check) {
                emails.forEach(e -> emailResultContains.put(e, finalGroupName));
                Set<String> temp = new HashSet<>(result.get(finalGroupName));
                temp.addAll(emails);
                result.put(finalGroupName, temp);
            } else {
                emails.forEach(e -> emailResultContains.put(e, finalGroupName));
                result.put(groupName, new HashSet<>(emails));
            }
        }
        return result;
    }

    /**
     * show map beautiful.
     *
     * @param userAndEmailsMap -
     */
    public void print(Map<String, Set<String>> userAndEmailsMap) {
        userAndEmailsMap.forEach((x, y) -> System.out.println(x + ": " + y));
    }

}
