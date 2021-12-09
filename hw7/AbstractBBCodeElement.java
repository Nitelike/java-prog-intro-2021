package markup;

import java.util.List;

public abstract class AbstractBBCodeElement implements BBCodeElement {
    protected String leftWrapper;
    protected String rightWrapper;
    protected List<? extends BBCodeElement> inner;

    public AbstractBBCodeElement(String leftWrapper, String rightWrapper, List<? extends BBCodeElement> inner) {
        this.leftWrapper = leftWrapper;
        this.rightWrapper = rightWrapper;
        this.inner = inner;
    }
    
    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(leftWrapper);
        for (BBCodeElement elem : inner) {
            elem.toBBCode(sb);
        }
        sb.append(rightWrapper);
    }
}