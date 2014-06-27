package cc4102.tarea3.io.log;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cc4102.tarea3.experiment.Experiment;

public abstract class Logger {
	PrintWriter writer;
	private final SimpleDateFormat timeFormat;
	
	public Logger() {
		timeFormat = new SimpleDateFormat("HH:mm:ss");
	}
	
	public void startLogging() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH.mm.ss");
			String fileName = getLogBasename() + sdf.format(new Date()) + "log.txt";
			
			writer = new PrintWriter(fileName, "UTF-8");
			String[] columns = getColumns();
			writer.write(columns[0]);
			for (int i = 1; i < columns.length; i++) {
				writer.write("\t"+columns[i]);
			}
			writer.write('\n');
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract String getLogBasename();
	
	public abstract String[] getColumns();
	
	protected String getFormattedTimestamp() {
		return timeFormat.format(new Date());
	}
	
	protected abstract String[] getFields(Experiment e);
	
	public void logResults(Experiment e) {
		StringBuilder sb = new StringBuilder();
		String[] fields = getFields(e);
		sb.append(fields[0]);
		for (int i = 1; i < fields.length; i++) {
			sb.append("\t").append(fields[i]);
		}
		sb.append('\n');
		writer.write(sb.toString());
		writer.flush();
	}

	public void finishLogging() {
		writer.close();
	}
	
}
