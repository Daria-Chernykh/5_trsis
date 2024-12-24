/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.rest.model.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> listAll();

    void delete(Integer id);

    StudentDTO add(String fullName, Integer taskOption, Integer number, Integer rating);

    StudentDTO findByFullName(String fullName);

}
