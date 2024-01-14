import React from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import CustomInput from "../CustomInput/CustomInput";
import { FieldInputProps } from "formik";

interface CustomDatePickerProps {
    field: FieldInputProps<Date | string>;
    form: {
        setFieldValue: (field: string, value: Date | string, shouldValidate?: boolean | undefined) => void;
    };
    onBlur?: (e: React.FocusEvent<HTMLInputElement>) => void;
}

const CustomDatePicker: React.FC<CustomDatePickerProps> = ({ field, form, onBlur }) => {
    const customInputRef = React.useRef(null);

    const formatDateToYYYYMMDD = (date: Date):string => {
        const year = date.getFullYear();
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const day = date.getDate().toString().padStart(2, '0');
        return `${month}-${day}-${year}`;
    };

    const fieldValueAsString = field.value ? formatDateToYYYYMMDD(new Date(field.value)) : '';

    return (
        <DatePicker
            onChange={(date: Date | null) => {
                if (date) {
                    form.setFieldValue(field.name, date.toISOString().split('T')[0]);
                } else {
                    form.setFieldValue(field.name, '');
                }
            }}
            customInput={<CustomInput
                ref={customInputRef}
                name={field.name}
                label="Birth Date"
                size="lg"
                borderRadius="lg"
                type="date"
                backgroundColor="background.base"
                color="background.light"
                hoverBackgroundColor="background.base"
                selectionBackgroundColor="gray"
                focusBoxShadow="0 0 0 3px rgba(66, 153, 225, 0.6)"
                placeholderStyle={{color: 'gray.400', fontSize: '2rem'}}
                width="100%"
                fontSize="xl"
                onBlur={onBlur}
                fontFamily="fonts.body"
                value={fieldValueAsString}
                onChange={() => {
                    form.setFieldValue(field.name, fieldValueAsString);
                }}
                sx={{ textDecoration: 'none' }}
            />}
        />
    );
};

export default CustomDatePicker;
