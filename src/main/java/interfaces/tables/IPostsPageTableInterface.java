package interfaces.tables;

import elements.rows.PostPageTableRow;

public interface IPostsPageTableInterface extends ITable{

    PostPageTableRow getRowByTitle(String rowTitle);

    boolean isTitleDraft(String title);
}
