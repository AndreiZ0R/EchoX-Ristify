import React, {useEffect, useState} from 'react';
import {useFormik} from "formik";
import {useNavigate} from "react-router-dom";
import * as Yup from 'yup';
import {Box, Button, Flex, Heading, IconButton, Image, Link, Radio, RadioGroup, Stack, Text, VStack} from "@chakra-ui/react";
import CustomInput from "../../components/CustomInput/CustomInput.tsx";
import CustomDatePicker from "../../components/CustomDatePicker/CustomDatePicker.tsx";
import CountrySelect from "../../components/CountrySelect/CountrySelect";
import {BiChevronLeftCircle, BiChevronRightCircle} from "react-icons/bi";
import {useRegister} from "../../hooks/CustomHooks.ts";
import {LoginModel, User} from "../../models/User.ts";

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
    const {mutate: register} = useRegister();

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

            register(registerValues, {
                onSuccess: (res) => {
                    const user: User = (res.payload as LoginModel).user;
                    navigate("/home", {state: {user}});
                }
            });
        }

    });

    const goToNextStep = () => setStep(step + 1);
    const goToPreviousStep = () => setStep(step - 1);
    const isLastStep = step === totalSteps;

    useEffect(() => {
        setIsUsernameValid(!formik.errors.username && fieldTouched.username);
        setIsEmailValid(!formik.errors.email && fieldTouched.email);
        setIsPasswordValid(!formik.errors.password && fieldTouched.password);
    }, [formik.isValid, formik.dirty]);

    return (
        // wrapper
        <Box
            display="flex"
            alignItems="center"
            justifyContent="center"
            bgGradient="linear(to-b, #845ec2, #c493ff, #242424)"
            h="100vh"
        >
            {/*login card*/}
            <Box
                h={{lg: "90%", md: "90%", sm: "full", base: "full"}}
                w={{lg: "75%", md: "75%", sm: "full", base: "full"}}
                bg="background.darker"
                boxShadow="0 0 25px 15px rgba(0, 0, 0, 0.35)"
                rounded={{lg: "3xl", md: "3xl", sm: "0", base: "0"}}
                // direction={{base: "column", md: "row"}}
                display="flex"
                flexDirection={{lg: "row", md: "row", sm: "column", base: "column"}}
            >

                {/* left img */}
                <Box
                    bg="background.base"
                    // w={{base: "auto", md: "50%"}}
                    h={{lg: "full", md: "full", sm: "30%", base: "30%"}}
                    w={{lg: "50%", md: "50%", sm: "full", base: "full"}}
                    // direction="column"
                    // align="center"
                    // justify="center"
                    rounded="3xl"
                >
                    <Image
                        src="../../assets/auth_back.jpg"
                        fit="cover"
                        w="100%"
                        h="100%"
                        rounded="3xl"
                    />
                </Box>

                {/* signing part */}
                <Box
                    h="full"
                    w={{lg: "50%", md: "50%", sm: "full", base: "full"}}
                >

                    <Flex
                        h="full"
                        w="full"
                        bg="background.darker"
                        align="center"
                        justify="center"
                        direction="column"
                        rounded="3xl"
                        p={5}
                    >
                        <VStack spacing={2} align="center" w="100%" h="full">
                            <Heading
                                as="h2"
                                size="2xl"
                                textColor="background.light"
                                sx={{
                                    userSelect: 'none',
                                    pointerEvents: 'none',
                                    textDecoration: 'none'
                                }}
                            >
                                Create an account
                            </Heading>
                            <Heading as="h3" size="md" textColor="primary.base" pb={2} mb={2}>
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

                            <form onSubmit={formik.handleSubmit}>
                                {/*step 1*/}
                                {step === 1 && (
                                    <Flex w="full" h="full" direction="column" gap={2}>
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
                                        >
                                            <Stack direction="row">
                                                <Radio value="User" size="md" colorScheme="primary.base">
                                                    <span style={{color: '#845ec2', fontSize: '18px'}}>User</span>
                                                </Radio>
                                                <Radio value="UserRole" size="md" colorScheme="primary.base">
                                                    <span style={{color: '#845ec2', fontSize: '18px'}}>UserRole</span>
                                                </Radio>
                                            </Stack>
                                        </RadioGroup>

                                    </Flex>
                                )}
                                {step === 2 && (
                                    <>
                                        <Flex
                                            direction="column"
                                            alignContent="center"
                                        >
                                            {formik.errors.firstName && fieldTouched.firstName && (
                                                <Text fontSize="12px" style={{color: 'red'}}>{formik.errors.firstName}</Text>
                                            )}
                                            <CustomInput
                                                label="First Name"
                                                size="md"
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
                                                <Text fontSize="12px" style={{color: 'red'}}>{formik.errors.lastName}</Text>
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
                                                fontSize="md"
                                                fontFamily="fonts.body"
                                            />
                                        </Flex>
                                        <Flex
                                            direction="column"
                                            alignContent="center"
                                        >
                                            {formik.errors.country && fieldTouched.country && (
                                                <Text fontSize="12px" style={{color: 'red'}}>{formik.errors.country}</Text>
                                            )}
                                            <CountrySelect
                                                label="Country"
                                                size="md"
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
                                            mt={5}
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
                                            mt={5}
                                            ml={5}
                                            w="250px"
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
                                            size="md"
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
                        </VStack>


                    </Flex>

                </Box>
            </Box>
        </Box>
    );
}

export default SignIn;