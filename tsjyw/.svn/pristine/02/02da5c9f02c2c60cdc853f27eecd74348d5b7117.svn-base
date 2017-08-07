package edu.iasd.search;

import java.util.ArrayList;
import java.util.List;

public class QueryModel {

	private List<ConditionItem> items;

	public List<ConditionItem> getItems() {
		return items;
	}

	public void setItems(List<ConditionItem> items) {
		this.items = items;
	}

	public QueryModel()	{
		items = new ArrayList<ConditionItem>();
	}
	
	public String toQueryString() {
		String condition = "";
		List<List<ConditionItem>> groups = new ArrayList<List<ConditionItem>>();
		for (ConditionItem item : items)
		{
			boolean added = false;
			for (List<ConditionItem> g : groups)
			{
				if (g.get(0).getOrGroup().equals(item.getOrGroup()))
				{
					g.add(item);
					added = true;
					break;
				}
			}
			if (!added)
			{
				List<ConditionItem> newg = new ArrayList<ConditionItem>();
				newg.add(item);
				groups.add(newg);
			}
		}

		for (List<ConditionItem> g : groups)
		{
			String c = "";
			for (ConditionItem i : g)
			{
				String s = i.toQueryString();
				if (c.isEmpty())
					c = s;
				else
					c += " and " + s;
			}

			if (g.size() > 1)
				c = "(" + c + ")";

			if (condition.isEmpty())
				condition = c;
			else
				condition += " or " + c;
		}

		return condition;
	}
}