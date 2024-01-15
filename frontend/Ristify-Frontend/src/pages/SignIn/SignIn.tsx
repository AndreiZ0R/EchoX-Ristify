import React, {useState} from 'react';
import {useFormik} from "formik";
import { useNavigate } from "react-router-dom";
import * as Yup from 'yup';
import {
    Button,
    Flex,
    VStack,
    Heading,
    Image,
    Link,
    Box,
    IconButton,
    Radio,
    RadioGroup,
    Stack
} from "@chakra-ui/react";
import CustomInput from "../../components/CustomInput/CustomInput.tsx";
import CustomDatePicker from "../../components/CustomDatePicker/CustomDatePicker.tsx";
import CountrySelect from "../../components/CountrySelect/CountrySelect";
import {BiChevronRightCircle, BiChevronLeftCircle} from "react-icons/bi";
import {useRegister} from "../../hooks/CustomHooks.ts";

const SignIn = () => {
    const [step, setStep] = useState(1);
    const totalSteps = 2;

    const [fieldTouched, setFieldTouched] = useState({
        username: false,
        email: false,
        password: false,
        firstName: false,
        lastName: false,
        country: false,
        birthDate: false,
    });

    const [isUsernameValid, setIsUsernameValid] = useState(false);
    const [isEmailValid, setIsEmailValid] = useState(false);
    const [isPasswordValid, setIsPasswordValid] = useState(false);
    const navigate = useNavigate();
    const register = useRegister();

    const formik = useFormik({
        initialValues: {
            username: '',
            firstName: '',
            lastName: '',
            email: '',
            country: '',
            birthDate: '',
            createdAt: new Date().toString().split('T')[0],
            role: "User",
            password: '',
        },
        validationSchema: Yup.object({
            username: Yup.string().required('Username is required'),
            firstName: Yup.string().required('First Name is required'),
            lastName: Yup.string().required('Last Name is required'),
            email: Yup.string().email('Invalid email address').required('Email is required'),
            country: Yup.string().required('Country is required'),
            birthDate: Yup.date().required('Birth date is required'),
            password: Yup.string().required('Password is required'),
            role: Yup.string().required('Role is required'),
        }),
        onSubmit: (values) => {
            const registerValues = {
                username: values.username,
                firstName: values.firstName,
                lastName: values.lastName,
                email: values.email,
                country: values.country,
                birthDate: new Date(values.birthDate),
                createdAt: new Date(values.createdAt),
                role: values.role,
                password: values.password,
            }
            register.mutate(registerValues, {
                onSuccess: () => {
                    navigate("/home")
                }
            });
        }

    });

    const goToNextStep = () => setStep(step + 1);
    const goToPreviousStep = () => setStep(step - 1);
    const isLastStep = step === totalSteps;

    React.useEffect(() => {
        setIsUsernameValid(!formik.errors.username && fieldTouched.username);
        setIsEmailValid(!formik.errors.email && fieldTouched.email);
        setIsPasswordValid(!formik.errors.password && fieldTouched.password);
    }, [formik.isValid, formik.dirty]);

    return (
        <Box
            display="flex"
            alignItems="center"
            justifyContent="center"
            bgGradient="linear(to-b, #845ec2, #c493ff, #242424)"
            h="100vh"
            overflow="auto"
        >
            <Flex
                h="75%"
                w="75%"
                bg="background.darker"
                boxShadow="0 0 25px 15px rgba(0, 0, 0, 0.35)"
                rounded="3xl"
                direction={{base: "column", md: "row"}}
                gap={6}
            >
                <Flex
                    bg="background.base"
                    w={{base: "auto", md: "50%"}}
                    h="100%"
                    direction="column"
                    align="center"
                    justify="center"
                    rounded="3xl"
                >
                    <Image
                        src="../../assets/auth_back.jpg"
                        objectFit="cover"
                        w="100%"
                        h="100%"
                        rounded="3xl"
                    />
                </Flex>
                <Flex
                    h="75%"
                    bg="background.darker"
                    align="center"
                    p={4}
                    direction="column"
                    rounded="3xl"
                    pl={100}
                >
                    <VStack spacing={4} align="center" w="100%">
                        <Heading
                            as="h2"
                            size="4xl"
                            textColor="background.light"
                            pt={30}
                            sx={{
                                userSelect: 'none',
                                pointerEvents: 'none',
                                textDecoration: 'none'
                            }}
                        >
                            Create an account
                        </Heading>
                        <Heading as="h3" size="lg" textColor="primary.base" pb={10}>
                            <span
                                style={{
                                    userSelect: 'none',
                                    pointerEvents: 'none',
                                    textDecoration: 'none'
                                }}
                            >
                                Already have an account?{' '}
                            </span>
                            <Link
                                color="primary.lighter"
                                href="/"
                                _hover={{textDecoration: 'none', color: "primary.base"}}
                            >
                                Login
                            </Link>
                        </Heading>
                        <Flex
                            w={{base: "auto", md: "80%"}}
                            h="80%"
                            direction="column"
                        >
                            <form onSubmit={formik.handleSubmit}>
                                {step === 1 && (
                                    <>
                                        <Flex
                                            direction="column"
                                            alignContent="center"
                                        >
                                            {formik.errors.username && fieldTouched.username && (
                                                <div style={{color: 'red'}}>{formik.errors.username}</div>
                                            )}
                                            <CustomInput
                                                label="Username"
                                                size="lg"
                                                borderRadius="lg"
                                                type="text"
                                                name="username"
                                                onChange={formik.handleChange}
                                                onBlur={() => setFieldTouched({...fieldTouched, username: true})}
                                                value={formik.values.username}
                                                backgroundColor="background.base"
                                                color="background.light"
                                                hoverBackgroundColor="background.base"
                                                selectionBackgroundColor="gray"
                                                focusBoxShadow="0 0 0 3px rgba(66, 153, 225, 0.6)"
                                                placeholderStyle={{color: 'gray.400', fontSize: '2rem'}}
                                                width="100%"
                                                fontSize="xl"
                                                fontFamily="fonts.body"
                                                autoComplete="off"
                                            />
                                        </Flex>
                                        <Flex
                                            direction="column"
                                            alignContent="center"
                                        >
                                            {formik.errors.email && fieldTouched.email && (
                                                <div style={{color: 'red'}}>{formik.errors.email}</div>
                                            )}
                                            <CustomInput
                                                label="Email"
                                                size="lg"
                                                borderRadius="lg"
                                                type="text"
                                                name="email"
                                                onChange={formik.handleChange}
                                                onBlur={() => setFieldTouched({...fieldTouched, email: true})}
                                                value={formik.values.email}
                                                backgroundColor="background.base"
                                                color="background.light"
                                                hoverBackgroundColor="background.base"
                                                selectionBackgroundColor="gray"
                                                focusBoxShadow="0 0 0 3px rgba(66, 153, 225, 0.6)"
                                                placeholderStyle={{color: 'gray.400', fontSize: '2rem'}}
                                                width="100%"
                                                fontSize="xl"
                                                fontFamily="fonts.body"
                                            />
                                        </Flex>
                                        <Flex
                                            direction="column"
                                            alignContent="center"
                                        >
                                            {formik.errors.password && fieldTouched.password && (
                                                <div style={{color: 'red'}}>{formik.errors.password}</div>
                                            )}
                                            <CustomInput
                                                label="Password"
                                                size="lg"
                                                borderRadius="lg"
                                                type="password"
                                                name="password"
                                                onChange={formik.handleChange}
                                                onBlur={() => setFieldTouched({...fieldTouched, password: true})}
                                                value={formik.values.password}
                                                backgroundColor="background.base"
                                                color="background.light"
                                                hoverBackgroundColor="background.base"
                                                selectionBackgroundColor="gray"
                                                focusBoxShadow="0 0 0 3px rgba(66, 153, 225, 0.6)"
                                                placeholderStyle={{color: 'gray.400', fontSize: '2rem'}}
                                                width="100%"
                                                fontSize="xl"
                                                fontFamily="fonts.body"
                                            />
                                        </Flex>
                                        <RadioGroup
                                            name="role"
                                            onChange={value => formik.setFieldValue('role', value)}
                                            value={formik.values.role}
                                            mb={20}
                                            mt={15}
                                        >
                                            <Stack direction="row">
                                                <Radio value="User" size="lg" colorScheme="primary.base">
                                                    <span style={{ color: '#845ec2', fontSize: '18px' }}>User</span>
                                                </Radio>
                                                <Radio value="UserRole" size="lg" colorScheme="primary.base">
                                                    <span style={{ color: '#845ec2', fontSize: '18px' }}>UserRole</span>
                                                </Radio>
                                            </Stack>
                                        </RadioGroup>

                                    </>
                                )}
                                {step === 2 && (
                                    <>
                                        <Flex
                                            direction="column"
                                            alignContent="center"
                                        >
                                            {formik.errors.firstName && fieldTouched.firstName && (
                                                <div style={{color: 'red'}}>{formik.errors.firstName}</div>
                                            )}
                                            <CustomInput
                                                label="First Name"
                                                size="lg"
                                                borderRadius="lg"
                                                type="firstName"
                                                name="firstName"
                                                onChange={formik.handleChange}
                                                onBlur={() => setFieldTouched({...fieldTouched, firstName: true})}
                                                value={formik.values.firstName}
                                                backgroundColor="background.base"
                                                color="background.light"
                                                hoverBackgroundColor="background.base"
                                                selectionBackgroundColor="gray"
                                                focusBoxShadow="0 0 0 3px rgba(66, 153, 225, 0.6)"
                                                placeholderStyle={{color: 'gray.400', fontSize: '2rem'}}
                                                width="100%"
                                                fontSize="xl"
                                                fontFamily="fonts.body"
                                            />
                                        </Flex>
                                        <Flex
                                            direction="column"
                                            alignContent="center"
                                        >
                                            {formik.errors.lastName && fieldTouched.lastName && (
                                                <div style={{color: 'red'}}>{formik.errors.lastName}</div>
                                            )}
                                            <CustomInput
                                                label="Last Name"
                                                size="lg"
                                                borderRadius="lg"
                                                type="lastName"
                                                name="lastName"
                                                onChange={formik.handleChange}
                                                onBlur={() => setFieldTouched({...fieldTouched, lastName: true})}
                                                value={formik.values.lastName}
                                                backgroundColor="background.base"
                                                color="background.light"
                                                hoverBackgroundColor="background.base"
                                                selectionBackgroundColor="gray"
                                                focusBoxShadow="0 0 0 3px rgba(66, 153, 225, 0.6)"
                                                placeholderStyle={{color: 'gray.400', fontSize: '2rem'}}
                                                width="100%"
                                                fontSize="xl"
                                                fontFamily="fonts.body"
                                            />
                                        </Flex>
                                        <Flex
                                            direction="column"
                                            alignContent="center"
                                        >
                                            {formik.errors.country && fieldTouched.country && (
                                                <div style={{color: 'red'}}>{formik.errors.country}</div>
                                            )}
                                            <CountrySelect
                                                label="Country"
                                                size="lg"
                                                borderRadius="lg"
                                                onChange={formik.handleChange}
                                                onBlur={() => setFieldTouched({...fieldTouched, country: true})}
                                                value={formik.values.country}
                                                backgroundColor="background.base"
                                                color="background.light"
                                                placeholder="Select Country"
                                                selectionBackgroundColor="gray"
                                                hoverBackgroundColor="background.base"
                                            />
                                        </Flex>
                                        <Flex
                                            direction="column"
                                            alignContent="center"
                                        >
                                            {formik.errors.birthDate && fieldTouched.birthDate && (
                                                <div style={{color: 'red'}}>{formik.errors.birthDate}</div>
                                            )}
                                            <CustomDatePicker
                                                field={formik.getFieldProps('birthDate')}
                                                form={{
                                                    setFieldValue: formik.setFieldValue,
                                                }}
                                                onBlur={() => setFieldTouched({...fieldTouched, birthDate: true})}
                                            />
                                        </Flex>
                                    </>
                                )}
                                <Flex
                                    w={{base: "auto", md: "80%"}}
                                    direction="row"
                                >
                                    {step > 1 && (
                                        <IconButton
                                            icon={<BiChevronLeftCircle/>}
                                            aria-label={isLastStep ? "Sign In" : "Next"}
                                            bgColor="primary.base"
                                            color="white"
                                            _hover={{backgroundColor: "primary.lighter"}}
                                            width="full"
                                            type="submit"
                                            mt={30}
                                            fontSize="xl"
                                            onClick={() => {
                                                formik.handleSubmit();
                                                goToPreviousStep();
                                            }}
                                            disabled={!formik.isValid || !formik.dirty}
                                        >
                                            Previous
                                        </IconButton>
                                    )}
                                    {isLastStep ? (
                                        <Button
                                            bgColor="primary.base"
                                            color="white"
                                            _hover={{backgroundColor: "primary.lighter"}}
                                            width="full"
                                            type="submit"
                                            size="lg"
                                            mt={30}
                                            ml={20}
                                            w="250%"
                                            fontSize="xl"
                                            disabled={!formik.isValid}
                                        >
                                            Sign In
                                        </Button>
                                    ) : (
                                        <IconButton
                                            bgColor="primary.base"
                                            color="white"
                                            _hover={{backgroundColor: "primary.lighter"}}
                                            width="full"
                                            type="submit"
                                            size="lg"
                                            fontSize="xl"
                                            icon={<BiChevronRightCircle/>}
                                            aria-label={isLastStep ? "Sign In" : "Next"}
                                            onClick={() => {
                                                if (isLastStep) {
                                                    formik.handleSubmit();
                                                } else {
                                                    if (!formik.isValid || !formik.dirty || (step !== 2 && (!isUsernameValid || !isEmailValid || !isPasswordValid))) {
                                                        formik.handleSubmit();
                                                        goToNextStep();
                                                    }
                                                }
                                            }}
                                            disabled={!formik.isValid || !formik.dirty || (step !== 2 && (!isUsernameValid || !isEmailValid || !isPasswordValid))}
                                        >
                                            {isLastStep ? "Sign In" : "Next"}
                                        </IconButton>
                                    )}
                                </Flex>
                            </form>
                        </Flex>
                    </VStack>
                </Flex>
            </Flex>
        </Box>
    );
}

export default SignIn;