package com.acs.adventofcode.puzzle9.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle9 {

	List<String> permuationList = new ArrayList<>();
	List<Route> allRoutes;
	Map<Integer, String> routeMap;
	
	public Puzzle9(){
		allRoutes = new ArrayList<>();
		routeMap = new HashMap<>();
	
	}
	public Route parseRoute(String line){
		Pattern pattern = Pattern.compile("([A-Za-z]+) to ([A-Za-z]+) = ([0-9]+)");
		Matcher matcher = pattern.matcher(line);
		
		if(matcher.matches()){
			String from = matcher.group(1);
			String to = matcher.group(2);
			String distance = matcher.group(3);
			
			Route r = new Route();
			r.setFrom(from);
			r.setTo(to);
			r.setDistance(Integer.parseInt(distance.trim()));
			allRoutes.add(r);
			return r;
		}
		
		return null;
	}

	public List<String> getAllStops() {
		List<String> allStops = new ArrayList<>();
		int i = 0;
		for (Route route : allRoutes) {
			String to = route.getTo();
			String from = route.getFrom();
			
			if(!allStops.contains(to)){
				allStops.add(to);
				if(!routeMap.containsKey(i)){
					routeMap.put(i, to);
					i++;
				}
			}
			
			if(!allStops.contains(from)){
				allStops.add(from);
				if(!routeMap.containsKey(i)){
					routeMap.put(i, from);
					i++;
				}
			}
			
		}
		return allStops;
	}
	public List<String> getPossibleRoutes() {
		
		StringBuilder sb = new StringBuilder();
		for (Entry<Integer, String> route : routeMap.entrySet()) {
			sb.append(route.getKey() + "");
		}

		String elements = sb.toString();//alphabet.substring(0, N);
        perm1(elements);
        
        for (String perm : permuationList) {
			char[] permKeys = perm.toCharArray();
			StringBuilder line = new StringBuilder();
			String start = routeMap.get(Integer.parseInt(permKeys[0] + ""));
			String middle = routeMap.get(Integer.parseInt(permKeys[1] + ""));
			String end = routeMap.get(Integer.parseInt(permKeys[2] + ""));
			
			for (Route route : allRoutes) {
				
				if(route.getFrom().equals(start) || route.getTo().equals(start)){
					System.out.println(route.getDistance());
				}
				
				if(route.getFrom().equals(end) || route.getTo().equals(end)){
					System.out.println(route.getDistance());
				}
				
				if(route.getFrom().equals(middle) || route.getTo().equals(middle)){
					System.out.println(route.getDistance());
				}
			}
			
			System.out.println(String.format("%s -> %s -> %s", start,middle,end));
		}

		
		
		
		return null;
	}
	
	  // print N! permutation of the characters of the string s (in order)
    public  void perm1(String s) { perm1("", s); }
    private void perm1(String prefix, String s) {
        int N = s.length();
        if (N == 0) {
//        	System.out.println(prefix);
        	if(!permuationList.contains(prefix)){
        		permuationList.add(prefix);
        	}
        }
        else {
            for (int i = 0; i < N; i++)
               perm1(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, N));
        }

    }

    // print N! permutation of the elements of array a (not in order)
    public static void perm2(String s) {
        int N = s.length();
        char[] a = new char[N];
        for (int i = 0; i < N; i++)
            a[i] = s.charAt(i);
        perm2(a, N);
    }

    private static void perm2(char[] a, int n) {
        if (n == 1) {
            System.out.println(a);
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(a, i, n-1);
            perm2(a, n-1);
            swap(a, i, n-1);
        }
    }  

    // swap the characters at indices i and j
    private static void swap(char[] a, int i, int j) {
        char c = a[i];
        a[i] = a[j];
        a[j] = c;
    }
}
