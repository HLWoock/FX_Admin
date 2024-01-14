package de.woock.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;


public class StopWatch {
	
	private final String id;
	
	private final Map<String, TaskInfo> tasks = new HashMap<>();
//	private final Set<TaskInfo> taskList = new HashSet<>();

	
	@Nullable
	private String currentTaskName;
	
	@Nullable
	private TaskInfo lastTaskInfo;

	private Long totalTimeNanos = 0L;

	public StopWatch(String id) {
		this.id = id;
	}


	public void start(String taskName) throws IllegalStateException {
		if (!tasks.containsKey(taskName)) {
			tasks.put(taskName, new TaskInfo(taskName, System.currentTimeMillis()));
		} else {
			TaskInfo taskInfo = tasks.get(taskName);
			taskInfo.numberOfCalls++;
		}
	}

	public void stop(String taskName) throws IllegalStateException {
		if (!tasks.containsKey(taskName)) {
			throw new IllegalStateException("Can't stop StopWatch: it's not running with name: " + taskName);
		}
		TaskInfo taskInfo = tasks.get(taskName);
		long lastTime   = System.currentTimeMillis() - taskInfo.startingPointInNanos;
		totalTimeNanos += lastTime;
		taskInfo.longesInterval   = Math.max(taskInfo.longesInterval  , lastTime);
		taskInfo.shortestInterval = Math.min(taskInfo.shortestInterval, lastTime);
		taskInfo.sum             += lastTime;
	}

	private TaskInfo[] getTaskInfo() {
		return sort(tasks);
	}
	
	private TaskInfo[] sort(Map<String, TaskInfo> map ) {
		
		List<Map.Entry<String, TaskInfo>> entries = new ArrayList<>(map.entrySet());
	    Collections.sort(entries, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));
	    Map<String, TaskInfo> sortedMap = new LinkedHashMap<>();
	    for (Map.Entry<String, TaskInfo> entry : entries) {
	        sortedMap.put(entry.getKey(), entry.getValue());
	    }
		
		return sortedMap.values().toArray(new TaskInfo[0]);
		
	}

	private String shortSummary() {
		return "StopWatch '" + id + "': running time = " + totalTimeNanos  + " ms";
	}

	public String prettyPrint() {
		StringBuilder sb = new StringBuilder(shortSummary());
		sb.append('\n');
		
		sb.append("-------------------------------------------------------------------------\n");
		sb.append("#      min         max      %      Task name\n");
		sb.append("-------------------------------------------------------------------------\n");
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMinimumIntegerDigits(6);
		nf.setGroupingUsed(true);
		NumberFormat af = NumberFormat.getNumberInstance();
		af.setMinimumIntegerDigits(5);
		af.setGroupingUsed(false);
		NumberFormat pf = NumberFormat.getPercentInstance();
		pf.setMinimumIntegerDigits(3);
		pf.setGroupingUsed(false);
		
		for (TaskInfo task : getTaskInfo()) {
			sb.append(af.format(task.getNumberOfCalls())).append("  ");
			
			sb.append(nf.format(task.getShortestInterval())).append("  ");
			sb.append(" - ");
			sb.append(nf.format(task.getLongesInterval()))  .append("  ");
			
			sb.append(pf.format(Math.round((double)task.sum / (double)totalTimeNanos))).append("  ");
			sb.append(task.getTaskName()).append('\n');
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(shortSummary());
		for (TaskInfo task : getTaskInfo()) {
			sb.append("; [").append(task.getTaskName()).append("] took ").append(task.getStartingPointInNanos()).append(" ms");
			long percent = Math.round(100 * task.sum / totalTimeNanos);
			sb.append(" = ").append(percent).append('%');
		}
		return sb.toString();
	}

	@Getter
	@Setter
	public static final class TaskInfo {

		private final String taskName;
		private final long   startingPointInNanos;
		public        long   numberOfCalls;
		public        long   longesInterval;
		public        long   shortestInterval;
		public        long   sum;
		
		TaskInfo(String taskName, long startzeitInNanos) {
			this.taskName             = taskName;
			this.startingPointInNanos = startzeitInNanos;
			this.longesInterval       = Long.MIN_VALUE;
			this.shortestInterval     = Long.MAX_VALUE;
			this.numberOfCalls        = 1;
		}
		
		public long getTimeMillis() {
			return TimeUnit.NANOSECONDS.toMillis(startingPointInNanos);
		}
		
		public double getTimeSeconds() {
			return startingPointInNanos / 1_000_000_000.0;
		}

		@Override
		public int hashCode() {
			return Objects.hash(taskName);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TaskInfo other = (TaskInfo) obj;
			return Objects.equals(taskName, other.taskName);
		}
	}
}
