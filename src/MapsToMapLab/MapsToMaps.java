package MapsToMapLab;

import java.util.*;
import java.util.stream.Collectors;

public class MapsToMaps {
    public static void main(String[] args) {
        mapsToMaps();
    }
    public static void mapsToMaps(){

        Map<String, Integer> channelToSubscribers    = new TreeMap<>(); // channel, numSubscribers

        Map<String, String> channelToPublisher       = new TreeMap<>(); // channel, publisher

        Map<String, Integer> publisherToSubscribers  = new TreeMap<>(); // publisher, numSubscribers

        // channel -> number of subscribers
        // K -> V1
        channelToSubscribers.put("JustForLaughs", 120_000);
        channelToSubscribers.put("JustForGags", 10_000);
        channelToSubscribers.put("ContemplationTechniques", 10_000);
        channelToSubscribers.put("A New Earth", 20_000);

        // channel -> publisher
        // K -> V2
        channelToPublisher.put("JustForLaughs", "Charlie Chaplin");
        channelToPublisher.put("JustForGags", "Charlie Chaplin");
        channelToPublisher.put("ContemplationTechniques", "Echhart Tolle");
        channelToPublisher.put("A New Earth", "Echhart Tolle");

        // 1. Setup "publisherToSubscribers"
        // publisher -> number of subscribers (total)
        // V2 -> V1

        channelToSubscribers.forEach((channel,numSubscriber) -> {
            String publisher = channelToPublisher.get(channel);
            if(publisherToSubscribers.containsKey(publisher)){
                numSubscriber+= publisherToSubscribers.get(publisher);
            }
            publisherToSubscribers.put(publisher,numSubscriber);
        });
        /* very complicated unnecessary solution using streams
        channelToPublisher.values()
                .stream()
                .distinct()
                .forEach( e -> publisherToSubscribers.put(e,0));


        publisherToSubscribers.forEach((publisher,totalSubscribers) -> {
            totalSubscribers= channelToPublisher.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().equals(publisher))
                    .map(Map.Entry::getKey)
                    .map(channelToSubscribers::get)
                    .mapToInt(numSubscriber->numSubscriber)
                    .sum();
            publisherToSubscribers.put(publisher,totalSubscribers);
        });*/


        // 2. Output "publisherToSubscribers"
        publisherToSubscribers.forEach((publisher,totalSubscribers) -> System.out.println("publisher : "+publisher + " totalSubscribers : "+totalSubscribers));

        // 3. Who has the most/least subscribers?

        int max = Collections.max(publisherToSubscribers.values());
        int min = Collections.min(publisherToSubscribers.values());
        publisherToSubscribers.forEach((publisher,totalSubscribers) -> {
            if(totalSubscribers == max ) System.out.println(publisher + " has max subs: " + totalSubscribers);
            else if(totalSubscribers == min ) System.out.println(publisher + " has min subs: " + totalSubscribers);
        });

        /* Not consistent, doesn't output all publishers
        String max = Collections.max(publisherToSubscribers.entrySet(), Map.Entry.comparingByValue()).getKey();
        String min = Collections.min(publisherToSubscribers.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.print("1.max : "+max+" 2.min : " + min);
        */
    }
}