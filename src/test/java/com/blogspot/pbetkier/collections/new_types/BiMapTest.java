package com.blogspot.pbetkier.collections.new_types;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.MinMaxPriorityQueue;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BiMapTest {

    // IP

    @Test
    public void shouldProvideNameToIpMappingAndReverse_JDK_iterating() {
        // given
        Map<String, String> nameToIp = new HashMap<>();
        nameToIp.put("github.com", "1.1.1.1");
        nameToIp.put("bitbucket.org", "2.2.2.2");

        // when
        String githubIP = nameToIp.get("github.com"); // O(1)
        String bitbucketName = null;
        for (String name : nameToIp.keySet()) { // O(n)
            if (nameToIp.get(name).equals("2.2.2.2")) {
                bitbucketName = name;
            }
        }

        // then
        assertThat(githubIP).isEqualTo("1.1.1.1");
        assertThat(bitbucketName).isEqualTo("bitbucket.org");
    }

    @Test
    public void shouldProvideNameToIpMappingAndReverse_JDK_two_maps() {
        // given
        Map<String, String> nameToIp = new HashMap<>();
        Map<String, String> ipToName = new HashMap<>();

        nameToIp.put("github.com", "1.1.1.1");
        ipToName.put("1.1.1.1", "github.com");

        nameToIp.put("bitbucket.org", "2.2.2.2");
        ipToName.put("2.2.2.2", "bitbucket.org"); // keep in sync or die

        // when
        String githubIP = nameToIp.get("github.com"); // O(1)
        String bitbucketName = ipToName.get("2.2.2.2"); // O(1)

        // then
        assertThat(githubIP).isEqualTo("1.1.1.1");
        assertThat(bitbucketName).isEqualTo("bitbucket.org");
    }

    @Test
    public void shouldProvideNameToIpMappingAndReverse_Guava() {
        // given
        BiMap<String, String> nameToIp = HashBiMap.create();
        nameToIp.put("github.com", "1.1.1.1");
        nameToIp.put("bitbucket.org", "2.2.2.2");

        // when
        String githubIP = nameToIp.get("github.com"); // O(1)
        String bitbucketName = nameToIp.inverse().get("2.2.2.2"); // O(1)

        // then
        assertThat(githubIP).isEqualTo("1.1.1.1");
        assertThat(bitbucketName).isEqualTo("bitbucket.org");
    }

}
