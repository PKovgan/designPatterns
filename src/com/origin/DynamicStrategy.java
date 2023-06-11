package com.origin;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

enum OutputFormat {
	MARKDOWN, HTML
}

interface ListStrategy {
	default void start(StringBuilder sb) {}
	void addListItem(StringBuilder stringBuilder, String item);
	default void end(StringBuilder sb) {}
}

class MarkdownListStrategy implements ListStrategy {
	@Override
	public void addListItem(StringBuilder stringBuilder, String item) {
		stringBuilder.append(" * ").append(item)
	    	.append(System.lineSeparator());
	}
}

class HtmlListStrategy implements ListStrategy {
	@Override
	public void start(StringBuilder sb) {
		sb.append("<ul>").append(System.lineSeparator());
	}

	@Override
	public void addListItem(StringBuilder stringBuilder, String item) {
		stringBuilder.append("  <li>")
			.append(item)
	    	.append("</li>")
	    	.append(System.lineSeparator());
	}

	@Override
	public void end(StringBuilder sb) {
	    sb.append("</ul>").append(System.lineSeparator());
	}
}

class TextProcessor	{
	private StringBuilder sb = new StringBuilder();
	private ListStrategy listStrategy;

	public TextProcessor(OutputFormat format) {
	    setOutputFormat(format);
	}

	public void setOutputFormat(OutputFormat format) {
		switch(format) {
		case MARKDOWN:
			listStrategy = new MarkdownListStrategy();
			break;
		case HTML: 
			listStrategy = new HtmlListStrategy();
			break;
		}
	}

	// the skeleton algorithm is here
	public void appendList(List<String> items) {
		listStrategy.start(sb);
		for (String item : items)
			listStrategy.addListItem(sb, item);
		listStrategy.end(sb);
	}

	public void clear() {
	    sb.setLength(0);
	}

	  @Override
	  public String toString()
	  {
	    return sb.toString();
	  }
	}

public class DynamicStrategy {
	  public static void main(String[] args)
	  {
	    TextProcessor tp = new TextProcessor(OutputFormat.MARKDOWN);
	    tp.appendList(Arrays.asList("liberte", "egalite", "fraternite"));
	    System.out.println(tp);
	    tp.setOutputFormat(OutputFormat.HTML);
	    tp.clear();
	    tp.appendList(Arrays.asList("inheritance", "encapsulation", "polymorphism"));
	    System.out.println(tp);
	    
	  }
	}
