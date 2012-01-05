package org.essembeh.rtfm.core.utils.list;

import java.io.File;

public class Test {
	public static void main(String[] args) {
		IdList<File, Identifier<File>> plop = new IdList<File, Identifier<File>>(new Identifier<File>() {
			@Override
			public String getId(File o) {
				return o.getAbsolutePath();
			}
		});

		System.out.println(plop);
		plop.add(new File("/"));
		System.out.println(plop);
		plop.add(new File("/"));
		System.out.println(plop);
		plop.add(new File("/plop"));
		System.out.println(plop);

		IdList<File, Identifier<File>> plop2 = plop.newEmptyOne();
		System.out.println(plop2);

		IdList<File, Identifier<File>> plop3 = new IdList<File, Identifier<File>>(plop);
		System.out.println(plop3);
	}
}
