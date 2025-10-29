package pt.ipp.isep.dei.repository;

import java.util.ArrayList;
import java.util.List;

import pt.ipp.isep.dei.domain.objects.Industry;

public class IndustryRepository {
    private List<Industry> industryList;

    public IndustryRepository() {
        industryList = new ArrayList<>();
    }

    public List<Industry> getIndustryList() {
        return industryList;
    }

    public void add(Industry industry) {
        industryList.add(industry);
    }

}
