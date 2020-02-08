package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.eduservice.entity.EduSubject;
import com.online.edu.eduservice.entity.dto.OneSubjectDto;
import com.online.edu.eduservice.entity.dto.TwoSubjectDto;
import com.online.edu.eduservice.exception.EduException;
import com.online.edu.eduservice.mapper.EduSubjectMapper;
import com.online.edu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-02-08
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public List<String> importPoiSubject(MultipartFile file) {

        //存储错误信息
        List<String> msg = new ArrayList<>();
        try {

            //获取文件的输入流
            InputStream in = file.getInputStream();

            //创建workbook
            XSSFWorkbook workbook = new XSSFWorkbook(in);

            //workbook获取sheet
            XSSFSheet sheet = workbook.getSheetAt(0);

            //sheet获取row
            //从第二行开始获取数据
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    String rowStr = "表格数据为空，请输入数据";
                    //msg.add(rowStr);
                    continue;
                }

                //row获取第一列cell
                Cell cellOne = row.getCell(0);
                //获取第一列cell的值
                if (cellOne == null) {
                    String cellStr = "第" + i + "行为空";
                    msg.add(cellStr);

                    //跳出本次循环，执行下次循环
                    continue;
                }
                //列不为空，第一列中的所有值
                String stringCellOneValue = cellOne.getStringCellValue();

                //不添加重复的一级分类，也就是title
                //添加到数据库中
                EduSubject oneSubject = existOneSubject(stringCellOneValue);
                String idOneParent = null;

                if (oneSubject == null) {
                    //把第一列中的所有值添加到数据库中
                    EduSubject eduSubject = new EduSubject();
                    eduSubject.setTitle(stringCellOneValue);
                    eduSubject.setParentId("0");
                    eduSubject.setSort(0);

                    baseMapper.insert(eduSubject);
                    //把新添加的一级id获取到并赋值
                    idOneParent = eduSubject.getId();
                } else {
                    //存在，不添加到数据库
                    idOneParent = oneSubject.getId();
                }

                //row获取第二列cell
                Cell cellTwo = row.getCell(1);
                //获取第二列cell的值
                if (cellTwo == null) {
                    String cellStr = "第" + i + "行为空";
                    msg.add(cellStr);

                    //跳出本次循环，执行下次循环
                    continue;
                }
                //列不为空,第二列中的所有值
                String stringCellTwoValue = cellTwo.getStringCellValue();
                //把第二列中的所有值添加到数据库中
                //不添加重复的一级分类，也就是title
                //添加到数据库中
                EduSubject oneSubject2 = existTwoSubject(stringCellTwoValue, idOneParent);

                if (oneSubject2 == null) {
                    //把第一列中的所有值添加到数据库中
                    EduSubject eduSubject = new EduSubject();
                    eduSubject.setTitle(stringCellTwoValue);
                    eduSubject.setParentId(idOneParent);
                    eduSubject.setSort(0);

                    //添加到数据库
                    baseMapper.insert(eduSubject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new EduException(20001, "导入失败出现的异常");
        }
        return msg;
    }

    /**
     * 返回所有的分类，封装要求的json格式
     *
     * @return
     */
    @Override
    public List<OneSubjectDto> getAllSubjectList() {
        //1 查询所有一级分类
        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("parent_id", "0");
        List<EduSubject> allOneSubjects = baseMapper.selectList(wrapper1);

        //2 查询所有二级分类
        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id", "0");
        List<EduSubject> allTwoSubjects = baseMapper.selectList(wrapper2);

        //创建list集合，用于存储所有一级分类
        List<OneSubjectDto> oneSubjectDtolist = new ArrayList<>();
        //3 首先构建一级分类
        //遍历所有的一级分类，得到每个EduSubject对象，把每个EduSubject对象转换OneSubjectDto
        for (int i = 0; i < allOneSubjects.size(); i++) {
            //获取每个EduSubject对象
            EduSubject eduOneSubject = allOneSubjects.get(i);
            //创建OneSubjectDto对象
            OneSubjectDto oneSubjectDto = new OneSubjectDto();
            //把每个EduSubject对象转换OneSubjectDto
            BeanUtils.copyProperties(eduOneSubject, oneSubjectDto);
            //把dto对象放到list集合
            oneSubjectDtolist.add(oneSubjectDto);

            //获取一级分类所有二级分类，List<TwoSubjectDto>
            //把所有的二级分类添加到每个一级分类对象中oneSubjectDto.setChildren(list);
            //创建list集合，用于存储二级分类
            List<TwoSubjectDto> twoSubjectDtoList = new ArrayList<>();
            //遍历所有的二级分类，得到每个二级分类
            for (int m = 0; m < allTwoSubjects.size(); m++) {
                //得到每个二级分类
                EduSubject eduTwoSubject = allTwoSubjects.get(m);
                //判断一级分类id和二级分类parentid是否一样
                if (eduTwoSubject.getParentId().equals(eduOneSubject.getId())) {
                    //二级分类转换TwoSubjectDto
                    TwoSubjectDto twoSubjectDto = new TwoSubjectDto();
                    //内省  反射
                    BeanUtils.copyProperties(eduTwoSubject, twoSubjectDto);
                    //放到list集合
                    twoSubjectDtoList.add(twoSubjectDto);
                }
            }
            //把二级分类放到每个一级分类中
            oneSubjectDto.setChildren(twoSubjectDtoList);
        }
        return oneSubjectDtolist;
    }

    @Override
    public boolean deleteSubjectById(String id) {

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);

        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            //一级分类下有二级分类，则不进行删除
            return false;
        } else {
            //一级分类下没有二级分类，则进行删除一级分类
            int delRow = baseMapper.deleteById(id);

            return delRow > 0;
        }
    }

    @Override
    public boolean addOneLevel(EduSubject eduSubject) {

        EduSubject oneSubject = this.existOneSubject(eduSubject.getTitle());
        if (oneSubject == null) {
            //表示数据库中没有此一级分类课程标题，添加
            eduSubject.setParentId("0");
            int insertRow = baseMapper.insert(eduSubject);

            return insertRow > 0;
        }
        return false;
    }

    @Override
    public boolean addTwoLevel(EduSubject eduSubject) {

        EduSubject twoSubject = this.existTwoSubject(eduSubject.getTitle(), eduSubject.getParentId());
        if (twoSubject == null) {
            int insertRowTwo = baseMapper.insert(eduSubject);
            return insertRowTwo > 0;
        }
        //表示数据库中已经有了二级分类科目，因不能重复导致添加失败
        return false;
    }

    /**
     * 判断数据库表subject中是否有存在的一级分类
     *
     * @param title
     * @return
     */
    private EduSubject existOneSubject(String title) {

        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();

        //拼接条件
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", "0");

        EduSubject eduSubject = baseMapper.selectOne(queryWrapper);

        return eduSubject;
    }


    /**
     * 判断数据库表subject中是否有存在的二级分类
     *
     * @param title
     * @return
     */
    private EduSubject existTwoSubject(String title, String parentId) {

        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();

        //拼接条件
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", parentId);

        EduSubject eduSubject = baseMapper.selectOne(queryWrapper);

        return eduSubject;
    }
}
